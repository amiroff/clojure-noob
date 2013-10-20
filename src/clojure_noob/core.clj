(ns clojure-noob.core
  (:gen-class))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; I am a comment

"I am a string and I
  can span multiple lines"

;; A keyword is like a symbol, except that keywords begin with a colon (:).
;; Key- words resolve to themselves. They are used to name things

:foo ;; :foo

;; Symbols refer to something

foo ;; CompilerException java.lang.RuntimeException: Unable to resolve symbol: foo in this context, compiling:(NO_SOURCE_PATH:0:0)

;; nil is null and false

(str nil) ; ""
(true? nil) ; false


(str "I concatenate" " strings")
(str 1 2 nil 3) ; str skips nil without error

["a" "b" #_you_can_ignore_me 43] ;; #_ is ignore syntax

;; symbol to var mappings

(def mysymbol "Hello world!")
(str mysymbol)

;; Quoting keeps something from being evaluated

(quote (println "foo")) ;; (println "foo")
(quote (+ 3 2)) ;; (+ 3 2)

;; ' is the shortcut for quote
(= 'a (quote a)) ;; true

;; lists

'(1 2 3 4)
(nth '(1 2 3 4) 3) ;; 4

;; Comma is iqual to space so can be used to improve readability when needed

(= '(1 2 3) '(1, 2, 3)) ;; true

;; vectors (aka arrays)

[3 2 1]
(get [3 2 1] 0) ;; 3
([3 2 1] 1) ;; 2 - vectors are functions

;; Maps (aka associative arrays)

(def inventors {"Lisp" "McCarthy" "Clojure" "Hickey"})

;; Maps are functions. If you pass a key to a map, it will return that key’s value,
;; or it will return nil if the key is not found:

(inventors "Lisp") ; McCarthy
(inventors "Foo")  ; nil

;; any Clojure data structure can be a key in a map
;; The fact that keywords resolve to themselves makes keywords useful as keys.

(def mymap ;; only a name..
  {:a 1
  :b "boring example"
  :c []
  "d" + })

;; Keywords are also functions. They take a map argument and look themselves up in the map.
(:b mymap) ;; "boring example"

;; we can also use get to lookup from map

(get mymap :a)
((get mymap "d") 2 3) ;5
(get mymap "c" "not found") ; get can provide default value if not found

{"name" "Metin" :surname "Amiroff"
 :favs {:music "Chillstep" :os "OSX" :lang "Clojure"}}

(:music (:favs {"name" "Metin" :surname "Amiroff"
                :favs {:music "Chillstep" :os "OSX" :lang "Clojure"}}))



;; sets are collections of unique values

#{} ;; empty set
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

(fn myfun [a b] (str a b) myfun) ;; we can provide a name for anon
                           ;; function if we plan to use it
                           ;; somewhere inside the function
                           ;; or maybe return function itself

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

(if 0 "ok" "not") ;; -> "ok"   0 is not false in clojure!


;; Regular Expressions


#"i-am-a-regular-expression"

(re-find #"^test" "retesting") ;; test
(re-find #"^test" "retesting") ;; nil

(clojure.string/replace "my-left-eye" #"left" "right") ;; my-right-eye

;; calling java methods

(. Cat meow) ; Cat.meow()
(. foo bar 3 1) ; foo.bar(3, 1)
(. (ack) bar "hello") ; ack().bar("hello")
(.toString 3) ; "3"

;; Instantiating java classes

(new class arguments*)
(new java.util.Date)
(new java.util.Timer false)