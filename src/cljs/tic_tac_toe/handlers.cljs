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
  )

(rf/reg-event-db
  :tile-clicked
  (fn [db [_ id]]
    (let [active-player- (sb/get-active-player-)]
      (-> (assoc-in db [:tiles id :clicked] @active-player-)
          (switch-player)))))

(rf/reg-event-db
  :reset-board
  (fn [db [_]]
    (let [tiles (get-in db [:tiles])]
      (println tiles)
      (->
        (assoc-in db [:tiles] (reduce (fn [tiles tile]
                                        (merge tiles (dissoc (second tile) :clicked)))
                                      tiles))
        (println)
        (assoc-in [:active-player] 1)))))