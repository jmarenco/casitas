package com.mym.myfirstapp.mvc;

import android.app.Activity;
import android.media.MediaPlayer;

import com.mym.myfirstapp.R;

import java.util.ArrayList;
import java.util.Random;

public class Sound
{
    // Actividad asociada
    private Activity _activity;

    // Conjuntos de sonidos
    private ArrayList<MediaPlayer> _conexiones;
    private ArrayList<MediaPlayer> _errores;
    private ArrayList<MediaPlayer> _felicitaciones;

    // Generador de numeros aleatorios
    private Random _random = new Random();

    // Constructor
    public Sound(Activity activity)
    {
        _activity = activity;

        _conexiones = new ArrayList<MediaPlayer>();
        _conexiones.add(MediaPlayer.create(_activity, R.raw.ding1));
        _conexiones.add(MediaPlayer.create(_activity, R.raw.ding2));
        _conexiones.add(MediaPlayer.create(_activity, R.raw.ding3));
        _conexiones.add(MediaPlayer.create(_activity, R.raw.ding4));

        _errores = new ArrayList<MediaPlayer>();
        _errores.add(MediaPlayer.create(_activity, R.raw.error1));
        _errores.add(MediaPlayer.create(_activity, R.raw.error2));
        _errores.add(MediaPlayer.create(_activity, R.raw.error3));
        _errores.add(MediaPlayer.create(_activity, R.raw.error4));

        _felicitaciones = new ArrayList<MediaPlayer>();
        _felicitaciones.add(MediaPlayer.create(_activity, R.raw.congrat1));
        _felicitaciones.add(MediaPlayer.create(_activity, R.raw.congrat2));
    }

    // Sonidos
    public void asignarServicio()
    {
        play(_conexiones);
    }
    public void advertirError()
    {
        play(_errores);
    }
    public void felicitar()
    {
        play(_felicitaciones);
    }

    // Reproduce un player aleatorio
    private void play(ArrayList<MediaPlayer> players)
    {
        int i = _random.nextInt(players.size());
        players.get(i).start();
    }
}
