package esdras.jahir.myfeelings.utilities

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import esdras.jahir.myfeelings.R

class CustomBarDrawable(private val context: Context, private val emocion: Emociones) : Drawable() {

    private var coordenadas: RectF? = null

    override fun draw(canvas: Canvas) {
        // Configurar pintura de fondo (gris)
        val fondo = Paint().apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            color = ContextCompat.getColor(context, R.color.gray)
        }

        // Definir dimensiones
        val ancho = (bounds.width() - PADDING).toFloat()
        val alto = (bounds.height() - PADDING).toFloat()

        // Definir coordenadas para la barra de fondo
        coordenadas = RectF(PADDING, PADDING, ancho, alto)

        // Dibujar el fondo
        coordenadas?.let { canvas.drawRect(it, fondo) }

        // Dibujar la barra de emoción si está presente
        if (emocion.porcentaje > 0) {
            val porcentaje = emocion.porcentaje * (bounds.width() - PADDING) / 100
            val coordenadas2 = RectF(PADDING, PADDING, porcentaje, alto)

            val seccion = Paint().apply {
                style = Paint.Style.FILL
                isAntiAlias = true
                color = ContextCompat.getColor(context, emocion.color)
            }

            canvas.drawRect(coordenadas2, seccion)
        }
    }

    override fun setAlpha(alpha: Int) {
        // Implementación opcional si se desea soporte de transparencia
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // Implementación opcional si se desean aplicar filtros de color
    }

    override fun getOpacity(): Int = PixelFormat.OPAQUE

    companion object {
        private const val PADDING = 10F
    }
}
