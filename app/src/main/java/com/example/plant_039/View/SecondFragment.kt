package com.example.plant_039.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.plant_039.R
import com.example.plant_039.ViewModel.FlowersViewModel
import com.example.plant_039.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {


    private lateinit var binding: FragmentSecondBinding
    private val viewModel : FlowersViewModel by activityViewModels()

    private var flowerId : Int = 0
    private var flowerName : String =""






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // recibo los valores del primer fragmento
        arguments?.let { bundle ->

            flowerId = bundle.getInt("FlowerId")
            flowerName = bundle.getString("FlowerName").toString()
            Log.d("bundle", "Bundle recibido: flowerid=$flowerId, flowerNombre=$flowerName")

        }

        // tengo que pasarle el id a la función del VIewModel

        flowerId.let { id ->
            viewModel.getFlowersDetailsByIdFromInternet(id)
        }


        // observo los cambios

        viewModel.getFlowersDetail().observe(viewLifecycleOwner) { flowerDetails ->


            if (flowerDetails != null) {

                Glide.with(binding.imageD).load(flowerDetails.imagenLink).into(binding.imageD)
                binding.textNameD.text =
                    binding.root.context.getString(R.string.NombreDetalles, flowerDetails.nombre)

                binding.textOrigenD.text =
                    binding.root.context.getString(R.string.OrigenDetalles, flowerDetails.origen)

                binding.textDescripcionD.text =
                    binding.root.context.getString(
                        R.string.DescripcionDetalles,
                        flowerDetails.descripcion
                    )

                binding.textPrecio.text =
                    binding.root.context.getString(
                        R.string.PrecioRv,
                        flowerDetails.precio.toString()
                    )

                binding.textAnioCreacionE.text =
                    binding.root.context.getString(
                        R.string.AnioCreacion,
                        flowerDetails.anioCreacion.toString()
                    )

                binding.textManual.text =
                    binding.root.context.getString(
                        R.string.ManualDesc,
                        if (flowerDetails.manual) "SI" else "NO"
                    )


                binding.fab.setOnClickListener {
                    enviarWhatsApp(flowerName)
                    Log.d("Button", "Contactar")

                }

            }


        }
    }
    fun enviarWhatsApp(nombre: String) {
        Log.d("fun", "función enviar WhatsApp")

        val numeroWhatsApp = "56912345678" // Número de destino con código de país, sin "+" ni espacios
        val mensaje = "Hola,\n" +
                "Quisiera cotizar sobre el siguiente instrumento musical: $nombre\n" +
                "Número contacto: ________\n" +
                "Gracias."

        // Crear el intent para WhatsApp
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroWhatsApp&text=${Uri.encode(mensaje)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        // Verificar si WhatsApp está instalado
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_SHORT).show()
            }
        }
        /*fun enviarCorreo(nombre: String) {
            Log.d("fun", "funcion enviar correo")
            val recipientEmail = "luci@plantapp.cl"
            val subject = "Consulta por Producto $nombre"
            val message = "Hola,\n\n" +
                    "Vi el producto $nombre y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número _________\n" + "Quedo atento."

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(Intent.createChooser(intent, "Enviar correo"))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error al enviar el correo", Toast.LENGTH_SHORT)
                    .show()
            }
        }*/






    override fun onDestroyView() {
        super.onDestroyView()
      //  _binding = null
    }
}