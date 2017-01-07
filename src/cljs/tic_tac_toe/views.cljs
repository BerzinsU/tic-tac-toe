(ns tic-tac-toe.views
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [tic-tac-toe.subs :as sb]))

(defn title []
  [:h1 {:style {:text-align "center"}}
   "TIC-TAC-TOE"])

(defn player-name [name]
  [:h2
   name])

(defn player-score [score]
  [:h3
   score])

(defn player-card [id]
  (let [player- (sb/get-player- id)]
    (fn []
      [:div {:style {:display        "flex"
                     :flex-direction "column"
                     :align-items    "center"
                     :flex           "1 0 auto"}}
       [player-name (:name @player-)]
       [player-score (:score @player-)]])))

(defn reset-button []
  [:button {:style    {:width  100
                       :height 25}
            :on-click #(rf/dispatch [:reset-board])}
   "Reset"])

(defn player-cards []
  [:div {:style {:display     "flex"
                 :align-items "center"}}
   [player-card 1]
   [reset-button]
   [player-card 2]])

(defn header [& contents]
  (into [:div {:style {}}]
        contents))

(defn board-tile [id]
  (let [clicked?- (sb/tile-clicked?- id)
        tile- (sb/tile- id)]
    (fn []
      (let [color (when @clicked?- (:color @(sb/get-player- @clicked?-)))]
        [:div {:style    (cond-> {:flex            "1 0 33%"
                                  :display         "flex"
                                  :align-items     "center"
                                  :justify-content "center"
                                  :max-width       180
                                  :max-height      180
                                  :border          "1px solid grey"}
                                 @clicked?- (assoc :background-color color))
               :on-click #(when (not @clicked?-)
                           (rf/dispatch [:tile-clicked id]))}
         @tile-]))))

(defn game-board []
  [:div {:style {:flex            "2 0 auto"
                 :display         "flex"
                 :justify-content "center"}}
   [:div {:style {:flex          "1 0 auto"
                  :display       "flex"
                  :flex-wrap     "wrap"
                  :height        "100vw"
                  :border        "1px solid grey"
                  :max-width     540
                  :max-height    540
                  :border-radius 3}}
    (for [n (range 1 10)]
      ^{:key n}
      [board-tile n])]])

(defn main-panel []
  [:div {:style {:display        "flex"
                 :min-height     "100%"
                 :font-family    "Avenir"
                 :flex-direction "column"}}
   [header
    [title]
    [player-cards]]
   [game-board]])
