(ns zine.api
  (:refer-clojure :exclude [defn])
  (:require [tailrecursion.castra :refer [defn]]))

(def posts
  [{:title   "Simple Man"
    :content "And be a simple, kind of man. Oh be something, you love and understand. Baby be a simple, kind of man."}
   {:title   "Champagne Supernova"
    :content "Someday you will find me. Caught beneath the landslide. In a champagne supernova. A champagne supernova in the sky"}])

(def initial-db-value
  {:posts posts})

(def db (atom initial-db-value))

(defn get-state []
  (let [db-val @db]
    {:posts (rseq (:posts db-val))}))
