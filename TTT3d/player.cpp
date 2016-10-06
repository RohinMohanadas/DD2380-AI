#include "player.hpp"
#include <cstdlib>
#include <time.h>

namespace TICTACTOE3D {

    int gamma(const GameState &pState, uint8_t currentPlayer) {
        int x = 0;
        int y = 0;
        int z = 0;
        int val = 0;
        uint8_t oppCtr = 0;
        uint8_t currCtr = 0;
        uint8_t mtCtr = 0;

        //columns, layer by layer
        for (z = 0; z < 4; z++) {
            for (x = 0; x < 4; x++) {
                currCtr = 0;
                mtCtr = 0;
                oppCtr = 0;
                for (y = 0; y < 4; y++) {
                    switch (pState.at(x, y, z)) {
                        case CELL_X:
                            currCtr++;
                            break;
                        case CELL_EMPTY:
                            mtCtr++;
                            break;
                        default:
                            oppCtr++;
                            break;
                    }
                }
                if (oppCtr == 4) {
                    val -= 1000;
                } else if (currCtr == 4) {
                    val += 1000;
                } else if (currCtr == 3 && mtCtr == 1) {
                    val += 100;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    val -= 100;
                } else if (currCtr == 2 && mtCtr == 2) {
                    val += 10;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    val -= 10;
                } else if (currCtr == 1 && mtCtr == 3) {
                    val += 1;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    val -= 1;
                }
            }
        }

        //rows layer by layer
        for (z = 0; z < 4; z++) {
            for (y = 0; y < 4; y++) {
                currCtr = 0;
                mtCtr = 0;
                oppCtr = 0;
                for (x = 0; x < 4; x++) {
                    switch (pState.at(x, y, z)) {
                        case CELL_X:
                            currCtr++;
                            break;
                        case CELL_EMPTY:
                            mtCtr++;
                            break;
                        default:
                            oppCtr++;
                            break;
                    }
                }
                if (oppCtr == 4) {
                    val -= 1000;
                } else if (currCtr == 4) {
                    val += 1000;
                } else if (currCtr == 3 && mtCtr == 1) {
                    val += 100;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    val -= 100;
                } else if (currCtr == 2 && mtCtr == 2) {
                    val += 10;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    val -= 10;
                } else if (currCtr == 1 && mtCtr == 3) {
                    val += 1;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    val -= 1;
                }
            }
        }
        //z pipe layer by layer (z pipes on xy plane))
        for (x = 0; x < 4; x++) {
            for (y = 0; y < 4; y++) {
                currCtr = 0;
                mtCtr = 0;
                oppCtr = 0;
                for (z = 0; z < 4; z++) {
                    switch (pState.at(x, y, z)) {
                        case CELL_X:
                            currCtr++;
                            break;
                        case CELL_EMPTY:
                            mtCtr++;
                            break;
                        default:
                            oppCtr++;
                            break;
                    }
                }
                if (oppCtr == 4) {
                    val -= 1000;
                } else if (currCtr == 4) {
                    val += 1000;
                } else if (currCtr == 3 && mtCtr == 1) {
                    val += 100;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    val -= 100;
                } else if (currCtr == 2 && mtCtr == 2) {
                    val += 10;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    val -= 10;
                } else if (currCtr == 1 && mtCtr == 3) {
                    val += 1;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    val -= 1;
                }
            }
        }

        //diagonals in xy plane
        for (z = 0; z < 4; z++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (x = 0; x < 4; x++) {
                switch (pState.at(x, x, z)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }

        for (z = 0; z < 4; z++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (x = 3; x >= 0; x--) {
                switch (pState.at(x, 3 - x, z)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }
        //diagonals in yz plane
        for (x = 0; x < 4; x++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (y = 0; y < 4; y++) {
                switch (pState.at(x, y, y)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }
        for (x = 0; x < 4; x++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (y = 3; y >= 0; y--) {
                switch (pState.at(x, 3 - y, y)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }
        //diagnoals in xz plane
        for (y = 0; y < 4; y++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (x = 0; x < 4; x++) {
                switch (pState.at(x, y, x)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }
        for (y = 0; y < 4; y++) {
            currCtr = 0;
            mtCtr = 0;
            oppCtr = 0;
            for (x = 3; x >= 0; x--) {
                switch (pState.at(x, y, 3 - x)) {
                    case CELL_X:
                        currCtr++;
                        break;
                    case CELL_EMPTY:
                        mtCtr++;
                        break;
                    default:
                        oppCtr++;
                        break;
                }

            }
            if (oppCtr == 4) {
                val -= 1000;
            } else if (currCtr == 4) {
                val += 1000;
            } else if (currCtr == 3 && mtCtr == 1) {
                val += 100;
            } else if (oppCtr == 3 && mtCtr == 1) {
                val -= 100;
            } else if (currCtr == 2 && mtCtr == 2) {
                val += 10;
            } else if (oppCtr == 2 && mtCtr == 2) {
                val -= 10;
            } else if (currCtr == 1 && mtCtr == 3) {
                val += 1;
            } else if (oppCtr == 1 && mtCtr == 3) {
                val -= 1;
            }
        }
        currCtr = 0;
        mtCtr = 0;
        oppCtr = 0;
        //main diagonals (1)
        for (z = 0; z < 4; z++) {
            switch (pState.at(z, z, z)) {
                case CELL_X:
                    currCtr++;
                    break;
                case CELL_EMPTY:
                    mtCtr++;
                    break;
                default:
                    oppCtr++;
                    break;
            }
        }
        if (oppCtr == 4) {
            val -= 1000;
        } else if (currCtr == 4) {
            val += 1000;
        } else if (currCtr == 3 && mtCtr == 1) {
            val += 100;
        } else if (oppCtr == 3 && mtCtr == 1) {
            val -= 100;
        } else if (currCtr == 2 && mtCtr == 2) {
            val += 10;
        } else if (oppCtr == 2 && mtCtr == 2) {
            val -= 10;
        } else if (currCtr == 1 && mtCtr == 3) {
            val += 1;
        } else if (oppCtr == 1 && mtCtr == 3) {
            val -= 1;
        }
        currCtr = 0;
        mtCtr = 0;
        oppCtr = 0;
        //main diagonals (2)
        for (x = 0; x < 4; x++) {
            switch (pState.at(x, 3 - x, 3 - x)) {
                case CELL_X:
                    currCtr++;
                    break;
                case CELL_EMPTY:
                    mtCtr++;
                    break;
                default:
                    oppCtr++;
                    break;
            }
        }
        if (oppCtr == 4) {
            val -= 1000;
        } else if (currCtr == 4) {
            val += 1000;
        } else if (currCtr == 3 && mtCtr == 1) {
            val += 100;
        } else if (oppCtr == 3 && mtCtr == 1) {
            val -= 100;
        } else if (currCtr == 2 && mtCtr == 2) {
            val += 10;
        } else if (oppCtr == 2 && mtCtr == 2) {
            val -= 10;
        } else if (currCtr == 1 && mtCtr == 3) {
            val += 1;
        } else if (oppCtr == 1 && mtCtr == 3) {
            val -= 1;
        }

        currCtr = 0;
        mtCtr = 0;
        oppCtr = 0;


        //main diagonals (3)
        for (y = 0; y < 4; y++) {
            switch (pState.at(3 - y, y, 3 - y)) {
                case CELL_X:
                    currCtr++;
                    break;
                case CELL_EMPTY:
                    mtCtr++;
                    break;
                default:
                    oppCtr++;
                    break;
            }
        }
        if (oppCtr == 4) {
            val -= 1000;
        } else if (currCtr == 4) {
            val += 1000;
        } else if (currCtr == 3 && mtCtr == 1) {
            val += 100;
        } else if (oppCtr == 3 && mtCtr == 1) {
            val -= 100;
        } else if (currCtr == 2 && mtCtr == 2) {
            val += 10;
        } else if (oppCtr == 2 && mtCtr == 2) {
            val -= 10;
        } else if (currCtr == 1 && mtCtr == 3) {
            val += 1;
        } else if (oppCtr == 1 && mtCtr == 3) {
            val -= 1;
        }

        currCtr = 0;
        mtCtr = 0;
        oppCtr = 0;
        for (z = 0; z < 4; z++) {
            switch (pState.at(3 - z, 3 - z, z)) {
                case CELL_X:
                    currCtr++;
                    break;
                case CELL_EMPTY:
                    mtCtr++;
                    break;
                default:
                    oppCtr++;
                    break;
            }
        }
        if (oppCtr == 4) {
            val -= 1000;
        } else if (currCtr == 4) {
            val += 1000;
        } else if (currCtr == 3 && mtCtr == 1) {
            val += 100;
        } else if (oppCtr == 3 && mtCtr == 1) {
            val -= 100;
        } else if (currCtr == 2 && mtCtr == 2) {
            val += 10;
        } else if (oppCtr == 2 && mtCtr == 2) {
            val -= 10;
        } else if (currCtr == 1 && mtCtr == 3) {
            val += 1;
        } else if (oppCtr == 1 && mtCtr == 3) {
            val -= 1;
        }
        if (currentPlayer == CELL_X) {
            return val;
        } else {
            return -val;
        }

    }

    int alphabeta(const GameState &pState, uint8_t depth, int alpha, int beta, uint8_t currentPlayer) {

        std::vector<GameState> lNextStates;

        int i = 0;
        int valToReturn = 0;
        if (depth == 0) {
            //            valToReturn = heuristicFunction(CELL_X, pState);
            valToReturn = gamma(pState, CELL_X);
        } else if (currentPlayer == CELL_X) {
            pState.findPossibleMoves(lNextStates);
            valToReturn = -999999999;
            for (i = 0; i < lNextStates.size(); i++) {
                valToReturn = std::max(valToReturn, alphabeta(lNextStates[i], depth - 1, alpha, beta, CELL_O));
                alpha = std::max(alpha, valToReturn);
                if (beta <= alpha) {
                    break; // beta prune
                }
            }
        } else {
            pState.findPossibleMoves(lNextStates);
            valToReturn = 999999999;
            for (i = 0; i < lNextStates.size(); i++) {
                valToReturn = std::min(valToReturn, alphabeta(lNextStates[i], depth - 1, alpha, beta, CELL_X));
                beta = std::min(beta, valToReturn);
                if (beta <= alpha) {
                    break; // alpha prune
                }
            }
        }
        return valToReturn;
    }

    GameState Player::play(const GameState &pState, const Deadline &pDue) {
        std::vector<GameState> lNextStates;
        pState.findPossibleMoves(lNextStates);
        uint8_t i = 0;
        uint8_t indx = 0;
        int alpha = -999999999;
        int beta = 999999999;
        int max = -999999999;
        int min = 999999999;
        int depth = 2;
        int valReturned = 0;
        if (lNextStates.size() == 0) {
            return GameState(pState, Move());
        } else {
            for (i = 0; i < lNextStates.size(); i++) {
                //maximize move.
                valReturned = alphabeta(lNextStates[i], depth, alpha, beta, CELL_O);
                if (valReturned > max) {
                    max = valReturned;
                    indx = i;
                }
            }
            return lNextStates[indx];
        }

        /*
         * Here you should write your clever algorithms to get the best next move, ie the best
         * next state. This skeleton returns a random move instead.
         */

    }

}
