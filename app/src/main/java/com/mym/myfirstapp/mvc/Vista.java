package com.mym.myfirstapp.mvc;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import com.mym.myfirstapp.MainActivity;
import com.mym.myfirstapp.R;
import com.mym.myfirstapp.negocio.Casita;
import com.mym.myfirstapp.negocio.Empresa;
import com.mym.myfirstapp.negocio.Nivel;
import com.mym.myfirstapp.negocio.Objeto;

import java.util.HashMap;
import java.util.Map;

public class Vista
{
    // Activity principal y controller
    private MainActivity _activity;
    private Controller _controller;

    // Canvas donde se dibuja
    private Bitmap _bitmap;
    private Canvas _canvas;
    private ImageView _imageView;
    private Paint _paint;

    // Ancho y altura del display y del nivel
    private double _anchoDisplay;
    private double _alturaDisplay;
    private double _anchoNivel;
    private double _alturaNivel;

    // Nivel actual
    private Nivel _nivel;

    // Imagenes
    private Map<Objeto, ImageView> _imagenes;

    // Constructor
    public Vista(Controller controller)
    {
        _controller = controller;
        _controller.setVista(this);

        _activity = _controller.getActivity();
        _nivel = _controller.getNivel();

        _anchoDisplay = _activity.getAnchoDisplay();
        _alturaDisplay = _activity.getAlturaDisplay();
        _anchoNivel = _nivel.getAncho();
        _alturaNivel = _nivel.getAltura();

        _imagenes = new HashMap<Objeto, ImageView>();
    }

    // Setters
    public void setBitmap(Bitmap bitmap, ImageView imageView)
    {
        _bitmap = bitmap;
        _canvas = new Canvas(bitmap);
        _imageView = imageView;

        _paint = new Paint();
        _paint.setColor(Color.BLUE);
        _paint.setStrokeWidth(3);
    }

    // Copia del bitmap
    private Bitmap _copiado;

    // Saca una foto del bitmap
    public void copiarBitmap()
    {
        _copiado = Bitmap.createBitmap(_bitmap);
    }

    // Restaura la foto del bitmap
    public void restaurarBitmap()
    {
        _imageView.setImageBitmap(_copiado);
        _bitmap = _copiado;
        _canvas = new Canvas(_bitmap);
        _copiado = null;
    }

    // Inicializa el nivel
    public void inicializar()
    {
        for(Empresa empresa: _nivel.getEmpresas())
        {
            ImageView imagen = _activity.crearImagen(R.id.image_id, empresa.getID(), convX(empresa), convY(empresa));
            _activity.crearImagen(R.id.image_id, empresa.getIdActivo(), convX(empresa)+30, convY(empresa)+30);

            _imagenes.put(empresa, imagen);
        }

        for(Casita casita: _nivel.getCasitas())
        {
            ImageView imagen = _activity.crearImagen(R.id.image_id, casita.getID(), convX(casita), convY(casita));
            _imagenes.put(casita, imagen);

            for(Empresa.Tipo tipo: casita.getNecesidades())
                dibujarServicio(casita, _nivel.getEmpresa(tipo));
        }

        _activity.crearImagenClickeable(R.id.restart_id, R.drawable.restart60, 5, 5);
    }

    // Muestra el servicio conectado en la casita
    public void dibujarServicio(Casita casita, Empresa empresa)
    {
        int id = casita.getServicio(empresa.getTipo()) ? empresa.getIdActivo() : empresa.getIdInactivo();
        int numero = casita.getNecesidades().indexOf(empresa.getTipo());

        _activity.crearImagen(R.id.image_id, id, convX(casita) + 10 + numero * 12, convY(casita) + 35);
    }

    // Convierte unidades del modelo a unidades en el display
    public int convX(Objeto objeto)
    {
        return (int)(objeto.getX() * _anchoDisplay / _anchoNivel);
    }
    public int convY(Objeto objeto)
    {
        return (int)(objeto.getY() * _alturaDisplay / _alturaNivel);
    }

    // Convierte unidades en el display a unidades del modelo
    public double pointX(float x)
    {
        return x * _anchoNivel / _anchoDisplay;
    }
    public double pointY(float y)
    {
        return y * _alturaNivel / _alturaDisplay;
    }

    // Informa si se puede dibujar el segmento sin cruzar un cable existente
    public boolean segmentoLibre(float x1, float y1, float x2, float y2)
    {
        double distancia = Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));

        if( distancia < 1 )
            return true;

        if( distancia < 3 )
            return pixelCableado(x2, y2);

        for(double alfa=1/distancia; alfa<=1; alfa += 1/distancia)
        {
            double x = (1 - alfa) * x1 + alfa * x2;
            double y = (1 - alfa) * y1 + alfa * y2;

            if( pixelCableado(x,y) )
                return false;
        }

        return true;
    }

    // Informa si el pixel está ocupado por un cable
    public boolean pixelCableado(double x, double y)
    {
        if( x < 0 || x >= _bitmap.getWidth() || y < 0 || y >= _bitmap.getHeight() )
            return true;

        return _bitmap.getPixel((int) x, (int) y) != 0;
    }

    // Dibuja una línea
    public void dibujar(float x1, float y1, float x2, float y2)
    {
        _canvas.drawLine(x1, y1, x2, y2, _paint);
        _imageView.invalidate();
    }

    // Objeto que incluye al punto (en coordenadas del display)
    @TargetApi(11)
    public Objeto objetoSeleccionado(float x, float y)
    {
        for(Objeto objeto: _imagenes.keySet())
        {
            ImageView imagen = _imagenes.get(objeto);

            if( imagen.getX() <= x && x <= imagen.getX() + imagen.getWidth() && imagen.getY() <= y && y <= imagen.getY() + imagen.getHeight())
                return objeto;
        }

        return null;
    }

    // Empresa y casita que incluye al punto
    public Empresa empresaSeleccionada(float x, float y)
    {
        Objeto ret = objetoSeleccionado(x,y);
        return ret instanceof Empresa ? (Empresa)ret : null;
    }
    public Casita casitaSeleccionada(float x, float y)
    {
        Objeto ret = objetoSeleccionado(x, y);
        return ret instanceof Casita ? (Casita)ret : null;
    }

    // Muestra el mensaje de nivel terminado
    public void terminarNivel()
    {
        _activity.crearImagen(R.id.image_id, R.drawable.welldone400, _anchoDisplay/2 - 150, _alturaDisplay/2 - 50);
        _activity.crearImagenClickeable(R.id.previous_id,  R.drawable.previous60, _anchoDisplay/2 - 50, _alturaDisplay/2 + 70);
        _activity.crearImagenClickeable(R.id.restart_id, R.drawable.restart60, _anchoDisplay/2, _alturaDisplay/2 + 70);
        _activity.crearImagenClickeable(R.id.next_id, R.drawable.next60, _anchoDisplay/2 + 50, _alturaDisplay/2 + 70);
    }

    // Reacciona ante clicks en imágenes
    public void click(int id)
    {
        if( id == R.id.previous_id )
            _controller.nivelAnterior();

        if( id == R.id.restart_id )
            _controller.reiniciar();

        if( id == R.id.next_id )
            _controller.nivelSiguiente();
    }
}