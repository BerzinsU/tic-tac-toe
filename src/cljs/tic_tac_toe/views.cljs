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

(defn reset-board-button []
  [:button {:style    {:width  100
                       :height 25}
            :on-click #(rf/dispatch [:reset-board])}
   "Reset board"])

(defn reset-score-button []
  [:button {:style    {:width         100
                       :height        25
                       :margin-bottom 15}
            :on-click #(rf/dispatch [:reset-score])}
   "Reset score"])

(defn player-cards []
  [:div {:style {:display     "flex"
                 :align-items "center"}}
   [player-card 1]
   [:div {:style {:display        "flex"
                  :flex-direction "column"}}
    [reset-score-button]
    [reset-board-button]]
   [player-card 2]])

(defn header [& contents]
  (into [:div {:style {}}]
        contents))

(defn circle []
  ;<svg width= "200" height= "200" >
  ;<circle class= "outer" cx= "95" cy= "95" r= "85" transform= "rotate(-90, 95, 95)" />
  ;</svg>
  [:svg {:width  140
         :height 140}
   [:circle {:style     {:fill                 "transparent"
                         :stroke               "green"
                         :stroke-width         15
                         :stroke-dasharray     534
                         :transition           "stroke-dashoffset 1s"
                         :animation-play-state "running"
                         :stroke-dashoffset    0
                         :animation            "showline 0.5s"}
             :cx        70
             :cy        70
             :r         60
             :transform "rotate(-90, 70, 70)"}]])

(defn cross []
  [:svg {:style  {:overflow "visible"}
         :width  110
         :height 110}
   [:line {:style {:fill                 "transparent"
                   :stroke               "blue"
                   :stroke-linecap       "round"
                   :stroke-width         15
                   :stroke-dasharray     534
                   :transition           "stroke-dashoffset 1s"
                   :animation-play-state "running"
                   :stroke-dashoffset    0
                   :animation            "showline 0.5s"}
           :x1    0
           :y1    0
           :x2    110
           :y2    110}]
   [:line {:style     {:fill                 "transparent"
                       :stroke               "blue"
                       :stroke-linecap       "round"
                       :stroke-width         15
                       :stroke-dasharray     534

                       :transition           "stroke-dashoffset 1s"
                       :animation-play-state "running"
                       :stroke-dashoffset    0
                       :animation            "showline2 0.5s"}
           :x1        0
           :y1        0
           :x2        110
           :y2        110
           :transform "rotate(90, 55, 55)"}]])

(defn tile-mark [player]
  (if (= 1 player)
    [circle]
    [cross]))

(defn tile-line-mark []
  (let [mark-color (sb/mark-color)]
    [:div {:style {:height           50
                   :position         "absolute"
                   :width            50
                   :border-radius    "50%"
                   :background-color mark-color}}]))

(defn board-tile [id]
  (let [clicked?- (sb/tile-clicked?- id)
        marked?- (sb/tile-marked?- id)
        game-state- (sb/game-state-)]
    (fn []
      (let [color (when @clicked?- (:color @(sb/get-player- @clicked?-)))]
        [:div {:style    {:flex            "1 0 33%"
                          :display         "flex"
                          :align-items     "center"
                          :justify-content "center"
                          :max-width       180
                          :max-height      180
                          :min-height 180
                          :border          "1px solid grey"}
               :on-click #(when (and (not @clicked?-)
                                     (= :play @game-state-))
                           (rf/dispatch [:tile-clicked id]))}
         (when @clicked?-
           [tile-mark @clicked?-])
         (when @marked?-
           [tile-line-mark])]))))

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
                 :font-family    "-apple-system, BlinkMacSystemFont, \"Segoe UI\", Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\""
                 :flex-direction "column"}}
   [header
    [title]
    [player-cards]]
   [game-board]])
