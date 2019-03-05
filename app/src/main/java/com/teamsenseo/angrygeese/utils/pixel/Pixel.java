package com.teamsenseo.angrygeese.utils.pixel;

import android.graphics.Bitmap;
import android.graphics.Picture;

import java.util.ArrayList;

/**
 * Class used for reading images
 *
 * @author Robert
 */
public final class Pixel {
    private final int color, x, y;

    public Pixel(final int color, final int x, final int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets all pixels from an image
     * TODO: Move android version to pie so it actually works
     */
    public static final ArrayList<Pixel> getPixels(final Picture p) {
        final ArrayList<Pixel> pixels = new ArrayList<>();
        final Bitmap map = Bitmap.createBitmap(p);

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                pixels.add(new Pixel(map.getPixel(x, y), x, y));
            }
        }

        return pixels;
    }

    /**
     * Gets the pixels color as an integer
     */
    public final int getColor() {
        return this.color;
    }

    /**
     * Gets the pixels x position
     */
    public final int getX() {
        return this.x;
    }

    /**
     * Gets the pixels y position
     */
    public final int getY() {
        return this.y;
    }
}
