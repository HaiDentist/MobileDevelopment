package com.dicoding.picodiploma.haidentist.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import com.dicoding.picodiploma.haidentist.ui.auth.login.LoginActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var preference: UserPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        preference = UserPreference.getInstance(requireContext().dataStore)

        view.findViewById<Button>(R.id.logout)?.setOnClickListener {
            viewModel.logout(preference)
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        preference = UserPreference.getInstance(requireContext().dataStore)






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        view?.findViewById<View>(R.id.privacy_policy)?.setOnClickListener {
            viewModel.logout(preference)
            val intent = Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        }
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}