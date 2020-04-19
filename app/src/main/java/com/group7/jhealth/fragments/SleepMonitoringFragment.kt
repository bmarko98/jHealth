package com.group7.jhealth.fragments

//import kotlinx.android.synthetic.main.fragment_sleep_monitoring
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
import com.group7.jhealth.R
import io.realm.Realm

class SleepMonitoringFragment : Fragment() {

    private lateinit var realm: Realm


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sleep_monitoring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        realm = Realm.getDefaultInstance()

/*        .setOnClickListener {
            findNavController().navigate(R.id.action_global_navigate_to_record_entry_fragment)
        }*/
    }
}
