package com.example.tommywahyu44.olc4.main

import com.example.tommywahyu44.olc4.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}
