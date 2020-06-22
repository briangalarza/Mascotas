package com.briangalarza.mascotas.view.tab.fragmentPhoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.util.loadImage


/**
 * Fragment para mostrar el tab de la foto y el nombre
 */
class PhotoFragment : Fragment() {

    private var pet: Pet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pet = arguments?.getParcelable(BUNDLE_ITEM)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_photo, container, false)

        val textViewTitle = root.findViewById<View>(R.id.name) as TextView
        val imageView = root.findViewById<View>(R.id.imageView) as ImageView

        pet?.let {
            if (it.photos.isNotEmpty()) imageView.loadImage(it.photos[0])
            textViewTitle.text = it.name
        }


        return root


    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private const val BUNDLE_ITEM = "Pet"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(pet: Pet): PhotoFragment {
            return PhotoFragment()
                .apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_ITEM, pet)
                }
            }
        }
    }
}