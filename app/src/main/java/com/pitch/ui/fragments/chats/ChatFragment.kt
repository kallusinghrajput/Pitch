package com.pitch.ui.fragments.chats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pitch.R
import com.pitch.data.model.ChatModelModel
import com.pitch.databinding.FragmentChatBinding
import com.pitch.ui.activities.home.HomeViewModel
import com.pitch.ui.adapter.ChatsAdapter
import com.pitch.ui.base.BaseFragment
import com.pitch.utils.DummyData
import com.pitch.utils.extention.changeStatusBarColor
import com.pitch.utils.extention.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, PeopleViewModel>() {

    override val viewModel: PeopleViewModel by viewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    val list = DummyData.chatList
    var adapter: ChatsAdapter? = null
    var isChats = true
    override fun getLayoutRes() = R.layout.fragment_chat
    override fun initViewModel(viewModel: PeopleViewModel) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        onClickListeners()
        homeViewModel.bottomProgressBar(true)
        blogAdapterInitialization()
        requireActivity().changeStatusBarColor(R.color.white, true)
    }

    private fun blogAdapterInitialization() {
        binding.apply {
            adapter = ChatsAdapter { model, position -> }
            rvPeople.adapter = adapter
            data(list)
        }
    }

    private fun onClickListeners() {
        binding.apply {
            toolbar.apply {
                tvTitle.text = "Tempus neque"
                ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            }

            ivSend.setOnClickListener {
                val message = etChat.text.toString().trim()
                if (message.isNotEmpty()) {
                    list.add(
                        0,
                        ChatModelModel(
                            isFirst = isChats,
                            isMe = true,
                            isChats = isChats,
                            message = message
                        )
                    )
                    isChats = false
                    data(list)
                    etChat.setText("")
                } else {
                    requireActivity().toast("Please type something")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().changeStatusBarColor(R.color.start_orange_color, false)
        homeViewModel.bottomProgressBar(false)
    }

    fun data(list: List<ChatModelModel>) {
        adapter!!.submitList(list.toMutableList())
        binding.rvPeople.smoothScrollToPosition(0)
    }
}