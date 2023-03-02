package com.pitch.utils

import com.pitch.data.enum.SideMenu
import com.pitch.data.model.ChatModelModel
import com.pitch.data.model.HomeModel
import com.pitch.data.model.RatingModel
import com.pitch.data.model.SideBarModel

object DummyData {

    val listOfProjects = mutableListOf<HomeModel>().apply {
        add(HomeModel(title = "Posted project", subTitle = "12"))
        add(HomeModel(title = "No. of chats", subTitle = "90"))
        add(HomeModel(title = "Awarded project", subTitle = "25"))

    }

    val list = mutableListOf<HomeModel>().apply {
        add(HomeModel())
        add(
            HomeModel(
                subTitle = "Non et duis felis ultrices ac amet sapien. Orci, bibendum quisque mauris mauris gravida neque, arcu elementum quam.",
                title = "Quis non"
            )
        )
        add(HomeModel())
        add(
            HomeModel(
                subTitle = "Non et duis felis ultrices ac amet sapien. Orci, bibendum quisque mauris mauris gravida neque, arcu elementum quam.",
                title = "Quis non"
            )
        )
        add(HomeModel())
        add(
            HomeModel(
                subTitle = "Non et duis felis ultrices ac amet sapien. Orci, bibendum quisque mauris mauris gravida neque, arcu elementum quam.",
                title = "Quis non"
            )
        )
        add(HomeModel())
        add(
            HomeModel(
                subTitle = "Non et duis felis ultrices ac amet sapien. Orci, bibendum quisque mauris mauris gravida neque, arcu elementum quam.",
                title = "Quis non"
            )
        )
    }


    val bidList = mutableListOf<RatingModel>().apply {
        add(RatingModel())
        add(RatingModel(rating = 3F))
        add(RatingModel(rating = 5F))
        add(RatingModel(rating = 1F))
        add(RatingModel(rating = 2F))
        add(RatingModel(rating = 4F))
    }

    val chatList = mutableListOf<ChatModelModel>().apply {
        add(
            ChatModelModel(
                isFirst = true,
                isMe = false,
                message = "Sem at condimentum maecenas facilisi ultrices\n" +
                        "egestas fusce in."
            )
        )

        add(
            ChatModelModel(
                isFirst = false,
                isMe = false,
                message = "Vitae donec fusce suspendi"
            )
        )
        add(
            ChatModelModel(
                isFirst = true,
                isMe = true,
                message = "Eu habitant eleifend donec urna, tortor blandit velit nisl"
            )
        )

        add(
            ChatModelModel(
                isFirst = true,
                isMe = false,
                message = "Eu habitant eleifend donec urna, tortor blandit velit nisl"
            )
        )

        add(
            ChatModelModel(
                isFirst = true,
                isMe = true,
                message = "Eu habitant eleifend donec urna, tortor blandit velit nisl"
            )
        )
        add(
            ChatModelModel(
                isChats = true,
                isFirst = false,
                isMe = true,
                message = "Eu habitant eleifend donec urna, tortor blandit velit nisl"
            )
        )
    }


    val sideList = mutableListOf<SideBarModel>().apply {
        add(SideBarModel(title = "Blogs", key = SideMenu.BLOG))
        add(SideBarModel(title = "Bids", key = SideMenu.BID))
        add(SideBarModel(title = "Dashboard", key = SideMenu.DASHBOARD))
       // add(SideBarModel(title = "Add projects", key = SideMenu.ADDProject))
        add(SideBarModel(title = "Logout", key = SideMenu.LOGOUT))
    }
}