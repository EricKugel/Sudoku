public class Grid {
    private Square[][] grid;

    public Grid() {
        this.grid = new Square[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new Square(row, col, 0);
            }
        }
    }

    public boolean isLegal() {
        for (int row = 0; row < 9; row++) {
            boolean[] hasNumber = new boolean[9]; 
            for (int col = 0; col < 9; col++) {
                int value = grid[row][col].getValue() - 1;
                if (value > -1) {
                    if (hasNumber[value]) {
                        return false;
                    } else {
                        hasNumber[value] = true;
                    }
                }
            }
        }

        for (int col = 0; col < 9; col++) {
            boolean[] hasNumber = new boolean[9]; 
            for (int row = 0; row < 9; row++) {
                int value = grid[row][col].getValue() - 1;
                if (value > -1) {
                    if (hasNumber[value]) {
                        return false;
                    } else {
                        hasNumber[value] = true;
                    }
                }
            }
        }

        for (int subRow = 0; subRow < 3; subRow++) {
            for (int subCol = 0; subCol < 3; subCol++) {
                boolean[] hasNumber = new boolean[9];
                for (int row = subRow * 3; row < subRow * 3 + 3; row++) {
                    for (int col = subCol * 3; col < subCol * 3 + 3; col++) {
                        int value = grid[row][col].getValue() - 1;
                        if (value > -1) {
                            if (hasNumber[value]) {
                                return false;
                            } else {
                                hasNumber[value] = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void solve() {
        int row = 0;
        int col = 0;
        while (true) {
            if (row > 8) {
                return;
            }

            while (grid[row][col].getValue() > 9) {
                grid[row][col].setValue(0);
                col -= 1;
                if (col < 0) {
                    col = 8;
                    row -= 1;
                }
                while(grid[row][col].isGiven()) {
                    col -= 1;
                    if (col < 0) {
                        col = 8;
                        row -= 1;
                    }
                }
                grid[row][col].setValue(grid[row][col].getValue() + 1);
            }

            if (!grid[row][col].isGiven()) {
                if (grid[row][col].getValue() == 0) {
                    grid[row][col].setValue(1);
                } 
                while(!isLegal()) {
                    grid[row][col].setValue(grid[row][col].getValue() + 1);
                    if (grid[row][col].getValue() > 9) {
                        break;
                    }
                }
                if (grid[row][col].getValue() <= 9) {
                    col += 1;
                    if (col > 8) {
                        col = 0;
                        row += 1;
                    }
                }
            } else {
                col += 1;
                if (col > 8) {
                    col = 0;
                    row += 1;
                }
            }
        }
    }

    public void initialize() {
        clear();
        for (int i = 0; i < 3; i++) {
            String numbers = "123456789";
            int[] randomNumbers = new int[9];
            for (int index = 0; index < randomNumbers.length; index++) {
                int randomIndex = (int) (Math.random() * numbers.length());
                randomNumbers[index] = Integer.parseInt("" + numbers.charAt(randomIndex));
                numbers = numbers.substring(0, randomIndex) + numbers.substring(randomIndex + 1);
            }
            int index = 0;
            for (int row = i * 3; row < i * 3 + 3; row++) {
                for (int col = i * 3; col < i * 3 + 3; col++) {
                    grid[row][col].setValue(randomNumbers[index]);
                    grid[row][col].setGiven(true);
                    index += 1;
                }   
            }
        }
        solve();
        removeSome();
    }

    public Square[][] getArray() {
        return grid;
    }

    public void removeSome() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setGiven(true);
                if (Math.random() > .37) {
                    grid[row][col].setValue(0);
                    grid[row][col].setGiven(false);
                }
            }
        }
    }

    public void clear() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].setValue(0);
                grid[row][col].setGiven(false);
            }
        }
    }

    public String generateHTML() {
        String html = "<table id = \"table\">";
        for (int row = 0; row < 9; row++) {
            html += "<tr>";
            for (int col = 0; col < 9; col++) {
                html += "<td";
                if (!grid[row][col].isGiven()) {
                    html += " class=\"red\"";
                }
                html += ">" + grid[row][col].getText() + "</td>";
            }
            html += "</tr>";   
        }
        html += "</table>";
        return html;
    }
}