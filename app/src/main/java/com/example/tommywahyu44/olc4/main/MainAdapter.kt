package com.example.tommywahyu44.olc4.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tommywahyu44.olc4.R
import com.example.tommywahyu44.olc4.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*

/**
 * Created by root on 1/16/18.
 */
class MainAdapter(val teamList: List<Team>, val context: Context?): RecyclerView.Adapter<MainAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(team: Team){
            itemView.tvTeam.text = team.teamName
            Picasso.get().load(team.teamBadge).into(itemView.imgTeam)
        }

    }
}