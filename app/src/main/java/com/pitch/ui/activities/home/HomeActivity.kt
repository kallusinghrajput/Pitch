package com.pitch.ui.activities.home

import android.content.Intent
import android.hardware.Camera
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.pitch.R
import com.pitch.api.apistate.Result
import com.pitch.data.enum.SideMenu
import com.pitch.databinding.ActivityHomeBinding
import com.pitch.ui.activities.authantication.AuthenticationActivity
import com.pitch.ui.activities.dialogs.ImagePickerDialogBox
import com.pitch.ui.adapter.SidebarAdapter
import com.pitch.ui.base.BaseActivity
import com.pitch.utils.*
import com.pitch.utils.ImageUtils.hideKeyboardData
import com.pitch.utils.extention.gone
import com.pitch.utils.extention.show
import com.pitch.utils.extention.showDialogs
import com.pitch.utils.extention.toast
import com.pitch.utils.timber.logE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class HomeActivity :
    BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    private val context = this
    lateinit var navControler: NavController
    override val viewModel: HomeViewModel by viewModels()
    private var camera: Camera? = null
    private var cameraId = 0
    override fun getLayoutRes() = R.layout.activity_home

    override fun initViewModel(viewModel: HomeViewModel) {
        binding.homeViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization()
        changeStatusBarColor(R.color.start_orange_color)
    }

    private fun initialization() {
        setBottomBar()
        observers()
        onClickListeners()
        setDrawerLayout()
        sideMenuData()
    }

    /**
     * this is for side drawer data
     */
    private fun sideMenuData() {
        val adapter = SidebarAdapter { model, _ ->
            lifecycleScope.launch {
                binding.drawerLayout.closeDrawers()
                delay(400)
                when (model.key) {
                    SideMenu.LOGOUT -> {
                        showDialogs(getString(R.string.logoutMessage)) {
                            startActivity(Intent(context, AuthenticationActivity::class.java))
                            toast("Your account logout successfully")
                            finish()
                        }
                    }
                    SideMenu.BID -> {
                        navControler.navigate(R.id.toBid)
                    }
                    SideMenu.DASHBOARD -> {
                        navControler.navigate(R.id.toDashboard)
                    }
                    SideMenu.BLOG -> {
                        navControler.navigate(R.id.toBlog)
                    }
                    SideMenu.ADDProject -> {
                        navControler.navigate(R.id.toAddProject)
                    }
                    null -> {}
                }
            }
        }
        binding.navigationView.rvMenu.adapter = adapter
        adapter.submitList(DummyData.sideList)
    }

    /**
     * all onclick listeners
     */
    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                ivMessage.setOnClickListener {
                    navControler.navigate(R.id.toPeople)
                }
                ivMenu.setOnClickListener {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }
        }
    }

    /**
     * all apis observer
     */
    private fun observers() {
        lifecycleScope.launch {
            viewModel.result.collectLatest {
                when (it) {
                    is Result.Error -> {
                        logE("observers error: ${it.message} ${it.errorData}")
                    }
                    is Result.Loading -> {
                        logE("observers loading: ")
                    }
                    is Result.Success -> {
                        logE("observers success: ${it.data}")
                    }
                }
            }
        }
    }

    /**
     * bottom bar manage
     */
    private fun setBottomBar() {
        binding.apply {
            bottomBar.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
            bottomBar.add(MeowBottomNavigation.Model(2, R.drawable.ic_plus))
            bottomBar.add(MeowBottomNavigation.Model(3, R.drawable.ic_wallet))
            bottomBar.add(MeowBottomNavigation.Model(4, R.drawable.ic_notification))
            bottomBar.add(MeowBottomNavigation.Model(5, R.drawable.ic_profile))
            toolbar.root.show()
            bottomBar.show(1)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) as NavHostFragment
            navControler = navHostFragment.navController


            bottomBar.setOnClickMenuListener { model: MeowBottomNavigation.Model? ->
                when (model!!.id) {
                    1 -> {
                        navControler.navigate(R.id.toHome)
                    }
                    2 -> {
                        navControler.navigate(R.id.toAdd)
                    }
                    3 -> {
                        navControler.navigate(R.id.toWallet)
                    }
                    4 -> {
                        navControler.navigate(R.id.toNotification)
                    }
                    5 -> {
                        navControler.navigate(R.id.toProfile)
                    }
                }
            }

            forDestination()

        }
    }

    /**
     * any destination has been change in navigation this was happen
     */
    private fun forDestination() {
        binding.apply {
            navControler.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.homeFragment ||
                    destination.id == R.id.walletFragment ||
                    destination.id == R.id.profileFragment ||
                    destination.id == R.id.notificationFragment
                    || destination.id == R.id.addFragment
                ) {
                    if (!bottomBar.isVisible) {
                        binding.bottomBar.slide(false)
                        bottomBar.show()
                    }
                } else {
                    if (bottomBar.isVisible) {
                        binding.bottomBar.slide(true)
                        bottomBar.gone()
                    }
                }

                if (destination.id == R.id.homeFragment) {
                    binding.toolbar.lLayout.slide(false)
                    toolbar.root.show()
                } else {
                    binding.toolbar.lLayout.slide(false)
                    toolbar.root.gone()
                }
            }
        }
    }


    /**
     * this is for open camera and gallery
     */
    fun openCameraAndGallery(click: (data: String, path: String) -> Unit) {
        ImagePickerDialogBox(object : ImagePickerDialogBox.UriInterface {
            override fun getCameraUri(fileUri: Uri?, imageName: String?) {
                click(fileUri.toString(), imageName.toString())
            }

            override fun getGalleryUri(uri: Uri, path: String) {
                click(uri.toString(), path)
            }

        }).show(supportFragmentManager, "tag")
    }


    /**
     * on back press
     */
    override fun onBackPressed() {
        when (navControler.currentDestination!!.id) {
            R.id.addFragment, R.id.walletFragment, R.id.profileFragment, R.id.notificationFragment -> {
                navControler.navigateUp()
                binding.bottomBar.show(1)
            }
            else -> {
                super.onBackPressed()
            }
        }

    }


    private fun ViewGroup.slide(isTrue: Boolean) {
        val transition =/* if (isTrue)*/ Slide(Gravity.START) /*else Slide(Gravity.END)*/
        transition.duration = 100
        TransitionManager.beginDelayedTransition(this, transition)
    }


    private fun setDrawerLayout() {
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                hideKeyboardData()
            }

            override fun onDrawerOpened(drawerView: View) {
                hideKeyboardData()
            }

            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) {}
        })

    }


}