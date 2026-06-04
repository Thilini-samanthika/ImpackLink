package com.example.impacklink

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.impacklink.adapter.DonationProjectAdapter
import com.example.impacklink.api.RetrofitClient
import model.Project
import model.ProjectResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationListFragment : Fragment() {
    
    private lateinit var rvProjects: RecyclerView
    private lateinit var adapter: DonationProjectAdapter
    private val projects = mutableListOf<Project>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donation_list, container, false)

        rvProjects = view.findViewById(R.id.rvDonationProjects)
        rvProjects.layoutManager = LinearLayoutManager(requireContext())
        
        setupAdapter()
        fetchProjects()
        setupNavigation(view)

        return view
    }

    private fun setupNavigation(view: View) {
        view.findViewById<View>(R.id.navHome).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        view.findViewById<View>(R.id.navGrid).setOnClickListener {
            // Handle Grid click
        }
        view.findViewById<View>(R.id.navSettings).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonarEditProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupAdapter() {
        adapter = DonationProjectAdapter(projects, { project ->
            // On Update Click
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UpdateDonationFragment())
                .addToBackStack(null)
                .commit()
        }, { project ->
            // On Delete Click
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DonationDeleteFragment())
                .addToBackStack(null)
                .commit()
        })
        rvProjects.adapter = adapter
    }

    private fun fetchProjects() {
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", 0)

        RetrofitClient.instance.getProjects(userId).enqueue(object : Callback<ProjectResponse> {
            override fun onResponse(call: Call<ProjectResponse>, response: Response<ProjectResponse>) {
                if (response.isSuccessful) {
                    response.body()?.projects?.let {
                        projects.clear()
                        projects.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(context, "Failed to load projects", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProjectResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
