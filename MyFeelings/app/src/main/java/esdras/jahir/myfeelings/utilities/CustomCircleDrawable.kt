package esdras.jahir.myfeelings.utilities

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import esdras.jahir.myfeelings.R

class CustomCircleDrawable(private val context: Context, private val emociones: ArrayList<Emociones>) : Drawable() {

    private var coordenadas: RectF? = null
    private var anguloBarrido: Float = 0.0F
    private var anguloInicio: Float = 0.0F
    private var grosorMetrica: Int = 0
    private var grosorFondo: Int = 0

    init {
        grosorMetrica = context.resources.getDimensionPixelSize(R.dimen.graphWidth)
        grosorFondo = context.resources.getDimensionPixelSize(R.dimen.graphBackground)
    }

    override fun draw(canvas: Canvas) {
        // Configurar el Paint para el fondo
        val fondo = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = grosorFondo.toFloat()
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            color = ContextCompat.getColor(context, R.color.gray)
        }

        // Definir el área de dibujo
        val ancho = (bounds.width() - PADDING).toFloat()
        val alto = (bounds.height() - PADDING).toFloat()
        coordenadas = RectF(PADDING, PADDING, ancho, alto)

        // Dibujar el fondo (círculo gris)
        coordenadas?.let {
            canvas.drawArc(it, 0.0F, 360.0F, false, fondo)
        }

        // Dibujar las emociones si existen
        if (emociones.isNotEmpty()) {
            for (e in emociones) {
                val degree: Float = (e.porcentaje * 360) / 100
                anguloBarrido = degree

                val seccion = Paint().apply {
                    style = Paint.Style.STROKE
                    isAntiAlias = true
                    strokeWidth = grosorMetrica.toFloat()
                    strokeCap = Paint.Cap.SQUARE
                    color = ContextCompat.getColor(context, e.color)
                }

                coordenadas?.let {
                    canvas.drawArc(it, anguloInicio, anguloBarrido, false, seccion)
                }

                anguloInicio += anguloBarrido
            }
        }
    }

    override fun setAlpha(alpha: Int) {
        // Implementación opcional
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // Implementación opcional
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    companion object {
        private const val PADDING = 25F
    }
}

