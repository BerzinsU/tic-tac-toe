(ns tic-tac-toe.db
  (:require [re-frame.db :refer [app-db]]
            [re-frame.core :as rf]
            [reagent.ratom :as ra :include-macros true]))

(def default-db
  {:name          "tic-tac-toe"
   :active-player 1
   :game-state    :play
   :mark-color    "red"
   :players       {1 {:name  "Player 1"
                      :score 0
                      :color "white"}
                   2 {:name  "Player 2"
                      :score 0
                      :color "blue"}}
   :tiles         {1 {:magic-number 2}
                   2 {:magic-number 7}
                   3 {:magic-number 6}
                   4 {:magic-number 9}
                   5 {:magic-number 5}
                   6 {:magic-number 1}
                   7 {:magic-number 4}
                   8 {:magic-number 3}
                   9 {:magic-number 8}}
   :rows          [[1 2 3]
                   [4 5 6]
                   [7 8 9]
                   [1 4 7]
                   [2 5 8]
                   [3 6 9]
                   [1 5 9]
                   [3 5 7]]})

