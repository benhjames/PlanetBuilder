package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.lang.Thread;

public class Noise {
    public static long[] hash(long[] key, int seed) {
        //hash function, based on murmur hash 3 implementation
        //key.length needs to be a multiple of 2, when key.length is 2, there arent any loops and hash is very fast
        long h1 = 0x9368e53c2f6af274L ^ seed;
        long h2 = 0x586dcd208f7cd3fdL ^ seed;
        long c1 = 0x87c37b91114253d5L;
        long c2 = 0x4cf5ad432745937fL;
        long k1;
        long k2;
        for (int i = 0; i < key.length / 2; i++) {
            k1 = key[i * 2];
            k2 = key[i * 2 + 1];

            k1 *= c1;
            k1 = (k1 << 23) | (k1 >>> 64 - 23);
            k1 *= c2;
            h1 ^= k1;
            h1 += h2;

            h2 = (h2 << 41) | (h2 >>> 64 - 41);

            k2 *= c2;
            k2 = (k2 << 23) | (k2 >>> 64 - 23);
            k2 *= c1;
            h2 ^= k2;
            h2 += h1;

            h1 = h1 * 3 + 0x52dce729;
            h2 = h2 * 3 + 0x38495ab5;

            c1 = c1 * 5 + 0x7b7d159c;
            c2 = c2 * 5 + 0x6bce6396;
        }

        h2 ^= key.length * 8;
        h1 += h2;
        h2 += h1;

        h1 ^= h1 >>> 33;
        h1 *= 0xff51afd7ed558ccdL;
        h1 ^= h1 >>> 33;
        h1 *= 0xc4ceb9fe1a85ec53L;
        h1 ^= h1 >>> 33;

        h2 ^= h2 >>> 33;
        h2 *= 0xff51afd7ed558ccdL;
        h2 ^= h2 >>> 33;
        h2 *= 0xc4ceb9fe1a85ec53L;
        h2 ^= h2 >>> 33;

        h1 += h2;
        h2 += h1;

        return new long[]{h1, h2};
    }

    private static final double F = (Math.sqrt(5.0) - 1.0) / 4.0;
    private static final double G = (5.0 - Math.sqrt(5.0)) / 20.0;

    private static int fastfloor(double x) {
        int xi = (int)x;
        return x < xi ? xi - 1 : xi;
    }
    private static int fastbool(boolean b) {
        return b ? 1 : 0;
    }

    public static double noise(double x, double y, double z, double w, int seed) {
        //simplex noise, based on implementation by stephen gustavson
        //very fast too, can do ten million calls per second
        double s = (x + y + z + w) * F;
        int i = fastfloor(x + s);
        int j = fastfloor(y + s);
        int k = fastfloor(z + s);
        int l = fastfloor(w + s);
        double t = (i + j + k + l) * G;
        double x0 = x - i + t;
        double y0 = y - j + t;
        double z0 = z - k + t;
        double w0 = w - l + t;
        int rankx = 0 + fastbool(x0 > y0) + fastbool(x0 > z0) + fastbool(x0 > w0);
        int ranky = 1 - fastbool(x0 > y0) + fastbool(y0 > z0) + fastbool(y0 > w0);
        int rankz = 2 - fastbool(x0 > z0) - fastbool(y0 > z0) + fastbool(z0 > w0);
        int rankw = 3 - fastbool(x0 > w0) - fastbool(y0 > w0) - fastbool(z0 > w0);
        int i1 = fastbool(rankx > 2);
        int j1 = fastbool(ranky > 2);
        int k1 = fastbool(rankz > 2);
        int l1 = fastbool(rankw > 2);
        int i2 = fastbool(rankx > 1);
        int j2 = fastbool(ranky > 1);
        int k2 = fastbool(rankz > 1);
        int l2 = fastbool(rankw > 1);
        int i3 = fastbool(rankx > 0);
        int j3 = fastbool(ranky > 0);
        int k3 = fastbool(rankz > 0);
        int l3 = fastbool(rankw > 0);
        double x1 = x0 - i1 + G;
        double y1 = y0 - j1 + G;
        double z1 = z0 - k1 + G;
        double w1 = w0 - l1 + G;
        double x2 = x0 - i2 + 2.0 * G;
        double y2 = y0 - j2 + 2.0 * G;
        double z2 = z0 - k2 + 2.0 * G;
        double w2 = w0 - l2 + 2.0 * G;
        double x3 = x0 - i3 + 3.0 * G;
        double y3 = y0 - j3 + 3.0 * G;
        double z3 = z0 - k3 + 3.0 * G;
        double w3 = w0 - l3 + 3.0 * G;
        double x4 = x0 - 1.0 + 4.0 * G;
        double y4 = y0 - 1.0 + 4.0 * G;
        double z4 = z0 - 1.0 + 4.0 * G;
        double w4 = w0 - 1.0 + 4.0 * G;
        long h0 = hash(new long[] {i +  0, j +  0, k +  0, l +  0}, seed)[0];
        long h1 = hash(new long[] {i + i1, j + j1, k + k1, l + l1}, seed)[0];
        long h2 = hash(new long[] {i + i2, j + j2, k + k2, l + l2}, seed)[0];
        long h3 = hash(new long[] {i + i3, j + j3, k + k3, l + l3}, seed)[0];
        long h4 = hash(new long[] {i +  1, j +  1, k +  1, l +  1}, seed)[0];
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
        double t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
        return 0.5 + 10.125 * (
                (t0 >= 0 ? t0 * t0 * t0 * t0 * ((((h0>>0)&2)-1) * x0 + (((h0>>1)&2)-1) * y0 + (((h0>>2)&2)-1) * z0 + (((h0>>3)&2)-1) * w0) : 0) +
                (t1 >= 0 ? t1 * t1 * t1 * t1 * ((((h1>>0)&2)-1) * x1 + (((h1>>1)&2)-1) * y1 + (((h1>>2)&2)-1) * z1 + (((h1>>3)&2)-1) * w1) : 0) +
                (t2 >= 0 ? t2 * t2 * t2 * t2 * ((((h2>>0)&2)-1) * x2 + (((h2>>1)&2)-1) * y2 + (((h2>>2)&2)-1) * z2 + (((h2>>3)&2)-1) * w2) : 0) +
                (t3 >= 0 ? t3 * t3 * t3 * t3 * ((((h3>>0)&2)-1) * x3 + (((h3>>1)&2)-1) * y3 + (((h3>>2)&2)-1) * z3 + (((h3>>3)&2)-1) * w3) : 0) +
                (t4 >= 0 ? t4 * t4 * t4 * t4 * ((((h4>>0)&2)-1) * x4 + (((h4>>1)&2)-1) * y4 + (((h4>>2)&2)-1) * z4 + (((h4>>3)&2)-1) * w4) : 0)
        );
    }

    public static double fractal_noise(double persistence, int iterations, double x, double y, double z, double w, int seed) {
        double frequency = 1;
        double amplitude = 1;
        double output = 0;
        double total = 0;
        for (int i = 0;i < iterations;i++) {
            output += amplitude * noise(frequency * x, frequency * y, frequency * z, frequency * w, seed);
            total += amplitude;
            frequency *= 2;
            amplitude *= persistence;
        }
        return output / total;
    }
}
