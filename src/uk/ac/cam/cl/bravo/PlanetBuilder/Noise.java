package uk.ac.cam.cl.bravo.PlanetBuilder;

public class Noise {
    public static long[] hash(final long[] key, final int seed) {
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
}
