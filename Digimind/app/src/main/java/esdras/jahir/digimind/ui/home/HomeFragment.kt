package esdras.jahir.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import esdras.jahir.digimind.R
import esdras.jahir.digimind.databinding.FragmentHomeBinding
import esdras.jahir.digimind.ui.Task

class HomeFragment : Fragment() {


    private var adaptador: AdaptadorTareas? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object{
        var task = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        if (first){
            fillTasks()
            first=false
        }

        adaptador = AdaptadorTareas(requireContext(), task)


        binding.gridView.adapter = adaptador

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillTasks() {
        task.add(Task("practica 1", arrayListOf("Monday", "Sunday"), "17:40"))
        task.add(Task("practica 2", arrayListOf("Tuesday", "Thursday"), "18:00"))
        task.add(Task("practica 3", arrayListOf("Wednesday", "Friday"), "19:30"))
        task.add(Task("practica 4", arrayListOf("Saturday", "Sunday"), "20:15"))
    }

    private class AdaptadorTareas(contexto: Context, tasks: ArrayList<Task>) : BaseAdapter() {
        private var tasks = tasks
        private var contexto: Context = contexto

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = tasks[position]
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.task_view, parent, false)

            val tv_title: TextView = vista.findViewById(R.id.tv_title)
            val tv_time: TextView = vista.findViewById(R.id.tv_time)
            val tv_days: TextView = vista.findViewById(R.id.tv_days)

            tv_title.text = task.title
            tv_time.text = task.time
            tv_days.text = task.days.joinToString(", ") // Para mostrar los días correctamente

            return vista
        }
    }
}

