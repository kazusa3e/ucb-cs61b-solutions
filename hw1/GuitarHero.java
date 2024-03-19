import synthesizer.GuitarString;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar
 * string sound
 */
public class GuitarHero {

    private static final double CONCERT_A = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        GuitarString[] guitarStrings = new GuitarString[KEYBOARD.length()];
        for (int ix = 0; ix != KEYBOARD.length(); ++ix) {
            guitarStrings[ix] = new GuitarString(
                    CONCERT_A * Math.pow(2, (ix - 24) / 12));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (KEYBOARD.indexOf(key) != -1)  {
                    guitarStrings[KEYBOARD.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int ix = 0; ix != guitarStrings.length; ++ix) {
                sample += guitarStrings[ix].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int ix = 0; ix != guitarStrings.length; ++ix) {
                guitarStrings[ix].tic();
            }
        }
    }
}
