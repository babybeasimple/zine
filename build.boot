#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.3.1"

(set-env!
  :project      'zine
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.1.2"]
                  [tailrecursion/hoplon      "5.7.1"]
                  [org.clojure/clojurescript "0.0-2156"]
                  [tailrecursion/boot.ring   "0.1.0-SNAPSHOT"]]
  :out-path     "resources/public"
  :src-paths    #{"src/hl" "src/cljs" "src/clj"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.castra.handler   :as c]
         '[tailrecursion.boot.task.ring   :as r])

(deftask castra
  [& specs]
  (r/ring-task (fn [_] (apply c/castra specs))))

(deftask development
  "Build zine for development."
  []
  (comp (watch) (hoplon {:prerender false})
        (r/head) (r/dev-mode) (r/session-cookie) (r/files)
        (castra 'zine.api) (r/jetty)))

(deftask production
  "Build zine for production."
  []
  (hoplon {:optimizations :advanced}))
