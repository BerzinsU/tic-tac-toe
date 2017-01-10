(ns tic-tac-toe.handlers
  (:require [re-frame.core :as rf]
            [tic-tac-toe.db :as db]
            [tic-tac-toe.subs :as sb]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(defn- switch-player [db]
  (let [active-player- (sb/get-active-player-)]
    (assoc-in db [:active-player] (quot 2 @active-player-))))

(defn- check-board-for-rows [db]
  (let [active-player- (sb/get-active-player-)
        tiles (get-in db [:tiles])
        rows (get-in db [:rows])]
    (println "Detecting full rows")
    (doall (for [row rows]
             (when (= 15 (->>
                           (select-keys tiles row)
                           (filter #(= @active-player- (:clicked (second %))))
                           (map (comp :magic-number second))
                           (apply +)))
               (rf/dispatch [:game-won @active-player- row]))))
    db))

(rf/reg-event-db
  :tile-clicked
  (fn [db [_ id]]
    (let [active-player- (sb/get-active-player-)]
      (-> (assoc-in db [:tiles id :clicked] @active-player-)
          (check-board-for-rows)
          (switch-player)))))

(rf/reg-event-db
  :reset-board
  (fn [db [_]]
    (let [tiles (get-in db [:tiles])]
      (->
        (assoc-in db [:tiles] (reduce (fn [tiles [index value]]
                                        (assoc tiles index (dissoc value :clicked)))
                                      {}
                                      tiles))

        (assoc-in [:active-player] 1)))))

(rf/reg-event-db
  :game-won
  (fn [db [_ player row]]
    (let [player-score @(sb/player-score- player)]
    (println (str "Player " player " won on row " row))
    (assoc-in db [:players player :score] (inc player-score)))))