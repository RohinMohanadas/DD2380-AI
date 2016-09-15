
class Player {

    public Player() {
    }

    /**
     * Shoot!
     *
     * This is the function where you start your work.
     *
     * You will receive a variable pState, which contains information about all
     * birds, both dead and alive. Each bird contains all past moves.
     *
     * The state also contains the scores for all players and the number of time
     * steps elapsed since the last time this function was called.
     *
     * @param pState the GameState object with observations etc
     * @param pDue time before which we must have returned
     * @return the prediction of a bird we want to shoot at, or cDontShoot to
     * pass
     */
    private static int ctr = 0;

    public Action shoot(GameState pState, Deadline pDue) {
        /*
         * Here you should write your clever algorithms to get the best action.
         * This skeleton never shoots.
         */

        //The current logic is to read as many observations as one can, 
        //i.e in our case, when pDue.remainingMs = pState.getNumBirds
        //This is with the intention of maximising iterations and getting
        //more training data. #suchAmaze
        //padayappa...
//        System.err.println("pDue:" + pDue.remainingMs() + " getNumBirds:" + pState.getNumBirds());
//        if (pDue.remainingMs() == pState.getNumBirds()) {
//            for (int i = 0; i < pState.getNumBirds(); i++) {
//                HMM bird = new HMM(pState.getBird(i));
//                bird.takeADecision();
//            }
//debugger.
        //reset bird counter
        int temp = 0;
        if (pState.getBird(ctr).getSeqLength() >= (100 - pState.getNumBirds())) {
//            System.err.println("Bird Num:" + pState.getNumBirds() + "Counter : " + ctr);
            HMM bird = new HMM(pState.getBird(ctr));
            int decision = bird.takeADecision();
            if (decision != -1) {
                if (ctr == pState.getNumBirds() - 1) {
                    temp = ctr;
                    ctr = 0;
                    return new Action(temp, decision);
                }
                return new Action(ctr++, decision);

            } else {

                if (ctr == pState.getNumBirds() - 1) {
                    temp = ctr;
                    ctr = 0;
                }
                ctr++;

            }
        }
        return cDontShoot;
//        }
        // This line chooses not to shoot.
//return new Action(0, Constants.MOVE_RIGHT);
        // This line would predict that bird 0 will move right and shoot at it.
        // return Action(0, MOVE_RIGHT);
    }

    /**
     * Guess the species! This function will be called at the end of each round,
     * to give you a chance to identify the species of the birds for extra
     * points.
     *
     * Fill the vector with guesses for the all birds. Use SPECIES_UNKNOWN to
     * avoid guessing.
     *
     * @param pState the GameState object with observations etc
     * @param pDue time before which we must have returned
     * @return a vector with guesses for all the birds
     */
    public int[] guess(GameState pState, Deadline pDue) {
        /*
         * Here you should write your clever algorithms to guess the species of
         * each bird. This skeleton makes no guesses, better safe than sorry!
         */

        int[] lGuess = new int[pState.getNumBirds()];
        for (int i = 0; i < pState.getNumBirds(); ++i) {
            lGuess[i] = Constants.SPECIES_UNKNOWN;
        }
        return lGuess;
    }

    /**
     * If you hit the bird you were trying to shoot, you will be notified
     * through this function.
     *
     * @param pState the GameState object with observations etc
     * @param pBird the bird you hit
     * @param pDue time before which we must have returned
     */
    public void hit(GameState pState, int pBird, Deadline pDue) {
        System.err.println("HIT BIRD!!!");
    }

    /**
     * If you made any guesses, you will find out the true species of those
     * birds through this function.
     *
     * @param pState the GameState object with observations etc
     * @param pSpecies the vector with species
     * @param pDue time before which we must have returned
     */
    public void reveal(GameState pState, int[] pSpecies, Deadline pDue) {
    }

    public static final Action cDontShoot = new Action(-1, -1);
}
