package com.briangalarza.mascotas.view.tab.fragmentDetails

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.util.loadImage
import kotlinx.android.synthetic.main.fragment_details.view.*


/**
 *
 */
class DetailsFragment : Fragment() {

    private var pet: Pet? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pet = arguments?.getParcelable(BUNDLE_ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        val textViewName = root.nameText

        val category = root.category

        val status = root.statusData


        pet?.let {
            textViewName.text = it.name

            when (it.status){
                "available" -> status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0);
            }
        }


        return root


    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param pet Pet.
         * @return A new instance of fragment DetailsFragment.
         */

        private const val BUNDLE_ITEM = "Pet"

        @JvmStatic
        fun newInstance(pet: Pet): DetailsFragment {
            return DetailsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putParcelable(BUNDLE_ITEM, pet)
                    }
                }
        }
    }
}
