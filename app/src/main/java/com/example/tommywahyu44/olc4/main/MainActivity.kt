package com.example.tommywahyu44.olc4.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.tommywahyu44.olc4.R
import com.example.tommywahyu44.olc4.R.array.league
import com.example.tommywahyu44.olc4.R.color.colorAccent
import com.example.tommywahyu44.olc4.api.ApiRepository
import com.example.tommywahyu44.olc4.model.Team
import com.example.tommywahyu44.olc4.util.invisible
import com.example.tommywahyu44.olc4.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeam.adapter = spinnerAdapter

        val layoutManager = GridLayoutManager(this, 3)
        rvTeam.layoutManager = layoutManager
        adapter = MainAdapter(teams,this)
        rvTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        leagueName = "English Premier League"
        presenter.getTeamList(leagueName)

        spinnerTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerTeam.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun showLoading() {
        mainProgressBar.visible()
        rvTeam.invisible()

    }

    override fun hideLoading() {
        mainProgressBar.invisible()
        rvTeam.visible()
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        rvTeam.adapter?.notifyDataSetChanged()
    }

}