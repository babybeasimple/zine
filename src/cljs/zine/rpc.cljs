(ns zine.rpc
  (:require-macros
    [tailrecursion.javelin :refer [defc defc=]])
  (:require
   [tailrecursion.javelin]
   [tailrecursion.castra :refer [mkremote]]))

(defc state {:posts []})
(defc error nil)
(defc loading [])

(defc= posts (get state :posts))

(def get-state
  (mkremote 'zine.api/get-state state error loading))

(defn init []
  (get-state))
