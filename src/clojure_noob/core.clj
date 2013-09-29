(ns clojure-noob.core
  (:gen-class))

;; basic functions

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

;; arity functions

(defn run
  "Describe how you run with arity function"
  ([how speed]
    (str "I run " how " and " speed "."))
  ([how]
    (run how " slow")))

(run "reqularly")
(run "seldom" "fast")

;; variable arity functions

(defn greet
  "Greet someone"
  [who]
  (str "Salut, " who "!"))

(defn greeter
  "Greet with variable-arity function"
  [& names]
  (map greet names))

(greeter "Rose" "Sarah" "Metin" "Alex")


(defn favourite-things
  [name & things]
  (str "Hi " name ". Here are my favourite things: "
    (clojure.string/join ", " things) "."))

(favourite-things "Josh" "music" "programming" "Islam")