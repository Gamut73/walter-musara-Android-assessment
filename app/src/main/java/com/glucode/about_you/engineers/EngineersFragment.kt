package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : Fragment() {
    private lateinit var binding: FragmentEngineersBinding
    private var lastSortMenuItemId: Int? = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpEngineersList(MockData.engineers)
        setupDivider()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_years -> {
                setUpEngineersList(MockData.engineers.sortedBy { it.quickStats.years }, item.itemId)
                true
            }

            R.id.action_coffees -> {
                setUpEngineersList(MockData.engineers.sortedBy { it.quickStats.coffees }, item.itemId)
                true
            }

            R.id.action_bugs -> {
                setUpEngineersList(MockData.engineers.sortedBy { it.quickStats.bugs }, item.itemId)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpEngineersList(engineers: List<Engineer>, sortMenuItemId: Int) {
        setUpEngineersList(if (lastSortMenuItemId == sortMenuItemId) engineers.reversed() else engineers)
        lastSortMenuItemId = sortMenuItemId
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {

        binding.list.adapter = EngineersRecyclerViewAdapter(engineers) {
            goToAbout(it)
        }
    }

    private fun setupDivider() {
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}