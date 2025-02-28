package esdras.jahir.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import esdras.jahir.digimind.R
import esdras.jahir.digimind.databinding.FragmentDashboardBinding
import esdras.jahir.digimind.ui.Task
import esdras.jahir.digimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time)
            }

            TimePickerDialog(
                root.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true // is24HourView
            ).show()
        }



        val btn_save = root.findViewById(R.id.btn_save) as Button
        val et_titulo = root.findViewById(R.id.et_task) as EditText
        val checkMonday = root.findViewById(R.id.checkMonday) as CheckBox
        val checkTuesday = root.findViewById(R.id.checkTuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.checkWednesday) as CheckBox
        val checkThursday = root.findViewById(R.id.checkThursday) as CheckBox
        val checkFriday = root.findViewById(R.id.checkFriday) as CheckBox
        val checkSaturday = root.findViewById(R.id.checkSaturday) as CheckBox
        val checkSunday = root.findViewById(R.id.checkSunday) as CheckBox

        btn_save.setOnClickListener{
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()

            val days = ArrayList<String>()

            if (checkMonday.isChecked) days.add("Monday")
            if (checkTuesday.isChecked) days.add("Tuesday")
            if (checkWednesday.isChecked) days.add("Wednesday")
            if (checkThursday.isChecked) days.add("Thursday")
            if (checkFriday.isChecked) days.add("Friday")
            if (checkSaturday.isChecked) days.add("Saturday")
            if (checkSunday.isChecked) days.add("Sunday")

            var task = Task(title, days, time)

            HomeFragment.task.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()
        }





        return root
    }

 override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}