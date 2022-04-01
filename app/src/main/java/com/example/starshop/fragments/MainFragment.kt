package com.example.starshop.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starshop.R
import com.example.starshop.adapters.ItemAdapter
import com.example.starshop.databinding.FragmentMainBinding
import com.example.starshop.viewmodel.MainViewModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(){
private lateinit var binding:FragmentMainBinding
private lateinit var model:MainViewModel
private val adapter = ItemAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.listItem.observe(viewLifecycleOwner) {
            adapter.updateList(it)
            binding.recyclerView.adapter = adapter
            binding.progressBar.visibility = View.INVISIBLE
        }
        //ToolBar Setting
        binding.toolbar.setNavigationIcon(R.drawable.drawer_menu)
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        binding.toolbar.setOnMenuItemClickListener { item ->
            if (item?.itemId == R.id.basket) findNavController().navigate(R.id.action_mainFragment_to_basketFragment)
            true
        }
        //Click on menu item in drawer
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nike -> {
                    model.cleanAdapter(adapter)
                    binding.progressBar.visibility = View.VISIBLE
                    model.addListNike()
                }
                R.id.adidas -> {
                    model.cleanAdapter(adapter)
                    binding.progressBar.visibility = View.VISIBLE
                    model.addListAdidas()
                }
                R.id.puma -> {
                    model.cleanAdapter(adapter)
                    binding.progressBar.visibility = View.VISIBLE
                    model.addListPuma()
                }
                R.id.nb -> {
                    model.cleanAdapter(adapter)
                    binding.progressBar.visibility = View.VISIBLE
                    model.addListNewBalance()
                }
                R.id.skechers -> null
            }
            binding.drawerLayout.close()
            true
        }
    }



}