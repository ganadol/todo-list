(ns todo-list.core
  (:gen-class)
  (:import (javax.swing JLabel JFrame JPanel JButton JTextField)
           (java.awt.event WindowListener)))


(defmacro with-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))


(defmacro on-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn rungui []
  (let [frame (JFrame. "todo list Demo")
        label (JLabel. "Exit on close")
        b-b1 (JButton. "Btn1")]
    (with-action b-b1 e
      (.setEnabled b-b1 false))
    (doto frame
      (.add label)
      (.setContentPane
       (doto (JPanel.)
         (.add b-b1)))
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      ;; (.addWindowListener
      ;;  (proxy [WindowListener] []
      ;;    (windowClosing [evt]
      ;;      (println "Whoop"))))
      (.pack)
      (.setVisible true))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (rungui)
  )
