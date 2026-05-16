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
2. Click any empty cell on the board to place it
3. Use **CHECK MOVES** to verify your answers
4. Use **SEE SOLUTION** if you're stuck
5. Click **NEW GAME** to fetch a fresh puzzle
6. **RESET** clears your moves without changing the puzzle

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