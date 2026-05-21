# 🧩 Sudoku Game — Java Swing

A fully functional Sudoku game built with Java Swing, featuring puzzle fetching from a live API, solution checking, and an auto-solver.

---

## 🚀 Features

- **9×9 Sudoku Board** built with Java Swing GUI
- **New Game** — fetches a fresh puzzle from a live API
- **Auto Solver** — solves the puzzle using backtracking
- **Check Moves** — highlights correct (green) and incorrect (red) cells
- **See Solution** — reveals the full solution instantly
- **Reset** — clears all user-entered values
- **Loading Screen** — shows a progress bar while fetching a new puzzle

---

## 🛠️ Tech Stack

- **Language:** Java
- **GUI:** Java Swing
- **JSON Parsing:** Google Gson
- **Puzzle API:** [Sudoku API](https://sudoku-api.vercel.app/)

---

## ▶️ How to Run

1. Clone the repository:
   git clone https://github.com/anshika-009/sudoku.git

2. Open the project in NetBeans

3. Run Sudoku.java

---

## 🎮 How to Play

1. Select a number (1-9) from the bottom panel

<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/b14ab7e0-9f05-42b9-8edc-3ec51cb413e5" />


2. Click any empty cell on the board to place it
   
<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/465327ee-4c91-4084-899a-686794cf77af" />


3. Use **CHECK MOVES** to verify your answers
   
<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/e237d929-1ef0-46fd-abf3-d684140c6a85" />


4. Use **SEE SOLUTION** if you're stuck

<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/51024ff1-7245-4b44-ac84-4826176c088a" />


6. Click **NEW GAME** to fetch a fresh puzzle

<img width="500" height="600" alt="image" src="https://github.com/user-attachments/assets/18b9bf4d-27d5-4707-810d-f9155958be4c" />


7. **RESET** clears your moves without changing the puzzle

---

## 🧠 Algorithm

The auto-solver uses **backtracking recursion**:
- Tries digits 1–9 in each empty cell
- Validates against row, column, and 3×3 box constraints
- Backtracks if no valid digit exists

Time complexity: **O(9^n)** worst case, where n = number of empty cells

---

## 📁 Project Structure

```
src/
└── com/mycompany/sudoku/
    └── Sudoku.java        # Main game file
```

---

## 🙌 Acknowledgements

- Puzzle data from [sudoku-api.vercel.app](https://sudoku-api.vercel.app/)
