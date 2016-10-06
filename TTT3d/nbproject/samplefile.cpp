/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
namespace TICTACTOE3D {

    int gamma2(const GameState &pState, uint8_t currentPlayer) {
        int x = 0;
        int y = 0;
        int z = 0;
        int maxVal = 0;
        int Xval = 0;
        int Oval = 0;
        uint8_t oppCtr = 0;
        uint8_t currCtr = 0;
        uint8_t mtCtr = 0;

        //        std::cerr << "I am here now 1" << std::endl;
        if (pState.isEOG()) {
            //Game over. Return something large
            return 9999999;
        }
        //            std::cerr << "I am here now" << std::endl;
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
                if (oppCtr == 3 && currCtr == 1) {
                    Xval += 99999;
                } else if (currCtr == 4) {
                    Xval += 9999999;
                } else if (currCtr == 3 && mtCtr == 1) {
                    Xval += 60000;
                } else if (currCtr == 2 && mtCtr == 2) {
                    Xval += 20000;
                } else if (currCtr == 1 && mtCtr == 3) {
                    Xval += 10000;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    Oval += 60000;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    Oval += 20000;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    Oval += 10000;
                } else if (oppCtr == 4) {
                    Oval += 9999999;
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
                if (oppCtr == 3 && currCtr == 1) {
                    Xval += 99999;
                } else if (currCtr == 4) {
                    Xval += 9999999;
                } else if (currCtr == 3 && mtCtr == 1) {
                    Xval += 60000;
                } else if (currCtr == 2 && mtCtr == 2) {
                    Xval += 20000;
                } else if (currCtr == 1 && mtCtr == 3) {
                    Xval += 10000;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    Oval += 60000;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    Oval += 20000;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    Oval += 10000;
                } else if (oppCtr == 4) {
                    Oval += 9999999;
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
                if (oppCtr == 3 && currCtr == 1) {
                    Xval += 99999;
                } else if (currCtr == 4) {
                    Xval += 9999999;
                } else if (currCtr == 3 && mtCtr == 1) {
                    Xval += 60000;
                } else if (currCtr == 2 && mtCtr == 2) {
                    Xval += 20000;
                } else if (currCtr == 1 && mtCtr == 3) {
                    Xval += 10000;
                } else if (oppCtr == 3 && mtCtr == 1) {
                    Oval += 60000;
                } else if (oppCtr == 2 && mtCtr == 2) {
                    Oval += 20000;
                } else if (oppCtr == 1 && mtCtr == 3) {
                    Oval += 10000;
                } else if (oppCtr == 4) {
                    Oval += 9999999;
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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
            }
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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
            }

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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
            }

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
            if (oppCtr == 3 && currCtr == 1) {
                Xval += 99999;
            } else if (currCtr == 4) {
                Xval += 9999999;
            } else if (currCtr == 3 && mtCtr == 1) {
                Xval += 60000;
            } else if (currCtr == 2 && mtCtr == 2) {
                Xval += 20000;
            } else if (currCtr == 1 && mtCtr == 3) {
                Xval += 10000;
            } else if (oppCtr == 3 && mtCtr == 1) {
                Oval += 60000;
            } else if (oppCtr == 2 && mtCtr == 2) {
                Oval += 20000;
            } else if (oppCtr == 1 && mtCtr == 3) {
                Oval += 10000;
            } else if (oppCtr == 4) {
                Oval += 9999999;
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
        if (oppCtr == 3 && currCtr == 1) {
            Xval += 99999;
        } else if (currCtr == 4) {
            Xval += 9999999;
        } else if (currCtr == 3 && mtCtr == 1) {
            Xval += 60000;
        } else if (currCtr == 2 && mtCtr == 2) {
            Xval += 20000;
        } else if (currCtr == 1 && mtCtr == 3) {
            Xval += 10000;
        } else if (oppCtr == 3 && mtCtr == 1) {
            Oval += 60000;
        } else if (oppCtr == 2 && mtCtr == 2) {
            Oval += 20000;
        } else if (oppCtr == 1 && mtCtr == 3) {
            Oval += 10000;
        } else if (oppCtr == 4) {
            Oval += 9999999;
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
        if (oppCtr == 3 && currCtr == 1) {
            Xval += 99999;
        } else if (currCtr == 4) {
            Xval += 9999999;
        } else if (currCtr == 3 && mtCtr == 1) {
            Xval += 60000;
        } else if (currCtr == 2 && mtCtr == 2) {
            Xval += 20000;
        } else if (currCtr == 1 && mtCtr == 3) {
            Xval += 10000;
        } else if (oppCtr == 3 && mtCtr == 1) {
            Oval += 60000;
        } else if (oppCtr == 2 && mtCtr == 2) {
            Oval += 20000;
        } else if (oppCtr == 1 && mtCtr == 3) {
            Oval += 10000;
        } else if (oppCtr == 4) {
            Oval += 9999999;
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
        if (oppCtr == 3 && currCtr == 1) {
            Xval += 99999;
        } else if (currCtr == 4) {
            Xval += 9999999;
        } else if (currCtr == 3 && mtCtr == 1) {
            Xval += 60000;
        } else if (currCtr == 2 && mtCtr == 2) {
            Xval += 20000;
        } else if (currCtr == 1 && mtCtr == 3) {
            Xval += 10000;
        } else if (oppCtr == 3 && mtCtr == 1) {
            Oval += 60000;
        } else if (oppCtr == 2 && mtCtr == 2) {
            Oval += 20000;
        } else if (oppCtr == 1 && mtCtr == 3) {
            Oval += 10000;
        } else if (oppCtr == 4) {
            Oval += 9999999;
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
        if (oppCtr == 3 && currCtr == 1) {
            Xval += 99999;
        } else if (currCtr == 4) {
            Xval += 9999999;
        } else if (currCtr == 3 && mtCtr == 1) {
            Xval += 60000;
        } else if (currCtr == 2 && mtCtr == 2) {
            Xval += 20000;
        } else if (currCtr == 1 && mtCtr == 3) {
            Xval += 10000;
        } else if (oppCtr == 3 && mtCtr == 1) {
            Oval += 60000;
        } else if (oppCtr == 2 && mtCtr == 2) {
            Oval += 20000;
        } else if (oppCtr == 1 && mtCtr == 3) {
            Oval += 10000;
        } else if (oppCtr == 4) {
            Oval += 9999999;
        }
        return Xval - Oval;

    }
}