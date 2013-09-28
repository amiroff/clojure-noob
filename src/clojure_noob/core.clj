(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn too-enthusiastic
  "Return a cheer that might sound a bit too enthusiastic"
  [name]
  (str "OH  MY GOD! " name " YOU ARE BEST MAN SLASH WOMAN "
   "EVER. WE SHOULD RUN AWAY!"))

(too-enthusiastic "Zelda")