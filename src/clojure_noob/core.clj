(ns clojure-noob.core
  (:gen-class))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; I am a comment

"I am a string and I
  can span multiple lines"

;; nil is null and false

(str nil) ; ""
(true? nil) ; false


(str "I concatenate" " strings")

["a" "b" #_you_can_ignore_me 43] ;; #_ is ignore syntax

;; symbols (variables)

(def mysymbol "Hello world!")
(str mysymbol)

;; Maps (aka associative arrays)

(def mymap ;; only a name..
  {:a 1
  :b "boring example"
  :c []
  "d" + })

(get mymap :a)
(:b mymap) ;; short version of above, works only with keywords
((get mymap "d") 2 3) ;5

{"name" "Metin" :surname "Amiroff"
 :favs {:music "Chillstep" :os "OSX" :lang "Clojure"}}

(:music (:favs {"name" "Metin" :surname "Amiroff"
                :favs {:music "Chillstep" :os "OSX" :lang "Clojure"}}))

;; vectors (aka arrays)

[3 2 1]
(get [3 2 1] 0) ;; 3
([3 2 1] 1) ;; 2 - vectors are functions

;; lists

'(1 2 3 4)
(nth '(1 2 3 4) 3) ;; 4

;; sets are collections of unique values

#{"hannah montanna" "miley cyrus" 20 45}
(conj #{:a :b} :b) ;; #{:a :b}
(get #{:a :b} :a) ;; :a

;; conjoin adds element to a sequence (place depends on concrete type)

(conj [1 2 3] 4) ;; [1 2 3 4]
(conj '(1 2 3) 4) ;; (4 1 2 3)
(conj {:a 1} {:b 3}) ;; {:b 3, :a 1}
(conj #{:a :b} :c)  ;; #{:a :c :b}
(conj [] 1) ;; [1]
(conj [1] 2 3) ;; [1 2 3]
(conj '(1) 2 3) ;; (3 2 1)

;; Sorting

(sort #{:a :c :b}) ;; (:a :b :c)
(sort {:b 0, :a 1}) ;; ([:a 1] [:b 0])

;; basic functions

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

(favourite-things "Movies" "music" "programming" "Monotheism") ;; arguments get treated as a list.

;; Anonymous functions

((fn [name]
 "Define and run anonymous function"
  (str "Hello " name)) "John")

(map (fn [name]
       (str "Hi, " name))
  ["Darth Vader" "Mr. Magoo"])

;; Super short anonymous function definition

(#(* % 3) 4)

(#(str %1 " and " %2) "corn bread" "butter beans")

;; Anonymous functions are cloSures

(defn greeter-maker
  "create a custom greeter"
  [greet-with]
  #(str greet-with % "!"))

(def hello-greeter (greeter-maker "Hello, "))
(def shalom-greeter (greeter-maker "Shalom, "))
(def assalam-greeter (greeter-maker "Asaalam aleykum, "))

(hello-greeter "Jesus")
(shalom-greeter "Moses")
(assalam-greeter "Mohammed")

;; let binds value on the right to the symbol on the left and returns last expression

(let [x 3]
  x) ;; 3

(let [x 3 y 5]
  (str x y)) ;; 35

(def dalmatian-list
  ["Pongo" "Missis" "Puppy 1" "Puppy 2"])

(let [dalmatians (take 2 dalmatian-list)]
  dalmatians) ;; ("Pongo" "Missis")

(let [[pongo & others] dalmatian-list]
  [pongo others]) ;; ["Pongo" ("Missis" "Puppy 1" "Puppy 2")]

;; looping

(loop [iteration 0] ;; start loop with default value of iteration set to 0
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration)))) ;; run the same anon function created by loop but this time incrementing a parameter


;; Regular Expressions


#"i-am-a-regular-expression"

(re-find #"^test" "retesting") ;; test
(re-find #"^test" "retesting") ;; nil

(clojure.string/replace "my-left-eye" #"left" "right") ;; my-right-eye