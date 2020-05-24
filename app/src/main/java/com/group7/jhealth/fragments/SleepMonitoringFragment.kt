package com.group7.jhealth.fragments

//import kotlinx.android.synthetic.main.fragment_sleep_monitoring
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.group7.jhealth.KEY_BUNDLE_INTAKE_HISTORY
import com.group7.jhealth.KEY_BUNDLE_SLEEP_DATA
import com.group7.jhealth.R
import com.group7.jhealth.database.SleepData
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_sleep_monitoring.*


/**
 * Class for Setting up the Sleep Monitoring option
 */
class SleepMonitoringFragment : Fragment() {
    private lateinit var listener: SleepMonitoringFragmentListener
    private lateinit var sleepData: SleepData
    private var happyCtr = 0
    private var mehCtr = 0
    private var sadCtr = 0

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sleep_monitoring, container, false)
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has returned, but before any saved state has been restored in to the view.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            this.sleepData = requireArguments().getParcelable<SleepData>(KEY_BUNDLE_SLEEP_DATA)!!
            happyCtr = sleepData.happySleepCtr
            mehCtr = sleepData.mehSleepCtr
            sadCtr = sleepData.sadSleepButton
        } catch (err: Exception) {
            err.printStackTrace()
        }

        happyButton.setOnClickListener {
            happyCtr++
            listener.updateDatabase(happyCtr, mehCtr, sadCtr)
        }

        mehButton.setOnClickListener {
            mehCtr++
            listener.updateDatabase(happyCtr, mehCtr, sadCtr)
        }

        sadButton.setOnClickListener {
            sadCtr++
            listener.updateDatabase(happyCtr, mehCtr, sadCtr)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as SleepMonitoringFragmentListener
        } catch (err: ClassCastException) {
            throw ClassCastException(("$context must implement SleepMonitoringFragmentListener"))
        }
    }

    interface SleepMonitoringFragmentListener {
        fun updateDatabase(happyCtr: Int, mehCtr: Int, sadCtr: Int)
    }
}
