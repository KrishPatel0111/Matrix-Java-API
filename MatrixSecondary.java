/**
 *
 * @author krish
 *
 */
public abstract class MatrixSecondary implements Matrix {

    @Override
    public void add(double[][] matrix) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row1 = this.getRow(i);
            double[] row2 = matrix[i - 1];
            for (int a = 1; a <= row1.length; a++) {
                row1[a - 1] += row2[a - 1];
            }
            temp.addRow(row1);
        }
        this.transferFrom(temp);
    }

    @Override
    public void multiplyByScalar(double scalar) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row1 = this.getRow(i);
            for (int a = 1; a <= row1.length; a++) {
                row1[a - 1] *= scalar;
            }
            temp.addRow(row1);
        }
        this.transferFrom(temp);
    }

    @Override
    public void multiply(double[][] matrix) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row1 = this.getRow(i);

            for (int a = 1; a <= matrix.length; a++) {
                row1[a - 1] *= matrix[a - 1][i - 1];
            }
            temp.addRow(row1);

        }
        this.transferFrom(temp);

    }

    @Override
    public void transpose() {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            temp.addColumn(this.getRow(i));
        }
        this.transferFrom(temp);
    }

    @Override
    public boolean isSymmetric() {
        double[] dimension = this.getDimension();
        boolean result = true;
        int i = 1;
        while (i <= dimension[0] && result) {
            double[] row = this.getRow(i);
            double[] column = this.getColumn(i);
            int j = 1;
            while (j <= row.length && result) {
                if (row[j - 1] != column[j - 1]) {
                    result = false;
                }
                j++;
            }
            i++;
        }
        return result;
    }

    @Override
    public boolean isZero() {
        double[] dimension = this.getDimension();
        boolean result = true;
        int i = 1;
        while (i <= dimension[0] && result) {
            double[] row = this.getRow(i);
            int j = 1;
            while (j <= row.length && result) {
                if (row[j - 1] != 0) {
                    result = false;
                }
                j++;
            }
            i++;
        }
        return result;
    }

    @Override
    public boolean isIdentity() {
        double[] dimension = this.getDimension();
        boolean result = true;
        int i = 1;
        while (i <= dimension[0] && result) {
            double[] row = this.getRow(i);
            int j = 1;
            while (j <= row.length && result) {
                if (i == j && row[j - 1] != 1) {
                    result = false;
                } else if (i != j && row[j - 1] != 0) {
                    result = false;
                }
                j++;
            }
            i++;
        }

        return result;
    }

    /**
     *
     * @param row
     * @param column
     * @param m
     * @return cofactor
     */

    private static double[][] cofactor(Matrix m, int row, int column) {
        double[] dimension = m.getDimension();
        double[][] temp = new double[(int) (dimension[0]
                - 1)][(int) (dimension[0] - 1)];
        int a = 0;
        int b = 0;
        for (int i = 1; i <= dimension[0]; i++) {
            double[] currentRow = m.getRow(i);
            for (int j = 1; j < dimension[0]; j++) {
                if (i != row && j != column) {
                    temp[a][b++] = currentRow[j];
                }
            }
        }
        return temp;
    }

    /**
     *
     * @param m
     * @return d
     */

    private static double determinant(Matrix m) {
        double d = 0;
        double[] dimension = m.getDimension();
        if (dimension[0] == 1) {
            double[] currentRow = m.getRow(1);
            d = currentRow[0];
        }
        int sign = 1;
        for (int i = 1; i <= dimension[0]; i++) {
            double[] currentRow = m.getRow(1);
            double[][] temp = cofactor(m, 1, i);
            //this error would go away after writing constructor
            d += sign * currentRow[i - 1] * determinant(new Matrix(temp));
            sign = -sign;
        }
        return d;

    }

    /**
     *
     * @param m
     * @return adjoint
     */

    private static double[][] adjoint(Matrix m) {
        double[] dimension = m.getDimension();
        double[][] adjoint = new double[(int) dimension[0]][(int) dimension[0]];
        if (dimension[0] == 1) {
            adjoint[0][0] = 1;

        }
        int sign = 1;
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row = m.getRow(i);
            for (int j = 1; j < dimension[0]; j++) {
                double[][] cofactor = cofactor(m, i, j);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adjoint[j][i] = sign * (determinant(new Matrix(cofactor)));
            }

        }

        return adjoint;
    }

    @Override
    public void inverse() {
        double det = determinant(this);
        double[][] adjoint = adjoint(this);
        Matrix temp = new Matrix(adjoint);
        temp.multiplyByScalar(1 / det);
        this.transferFrom(temp);

    }

    @Override
    public boolean isInvertible() {
        boolean result = true;
        if (this.determinant() == 0) {
            result = false;
        }
        return result;

    }

    @Override
    public void operation1(int a, int b) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 2; i <= dimension[0]; i++) {
            if (i != a && i != b) {
                temp.addRow(this.getRow(i));
            } else if (i == a) {
                temp.addRow(this.getRow(b));
            } else if (i == b) {
                temp.addRow(this.getRow(a));
            }
        }
        this.transferFrom(temp);
    }

    @Override
    public void operation2(int b, double scalar) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row = this.getRow(i);
            if (i == b) {
                for (int j = 1; j <= row.length; i++) {
                    row[j - 1] *= scalar;
                }
            }
            temp.addRow(row);
        }
        this.transferFrom(temp);
    }

    @Override
    public void operation3(int a, int b, double scalar) {
        Matrix temp = this.newInstance();
        double[] dimension = this.getDimension();
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row = this.getRow(i);
            if (i == a) {
                double[] rowAdded = this.getRow(b);
                for (int j = 1; j <= row.length; i++) {
                    row[j - 1] += scalar * rowAdded[j - 1];
                }
            }
            temp.addRow(row);
        }
        this.transferFrom(temp);
    }

    @Override
    public String toString() { //could be improved
        double[] dimension = this.getDimension();
        String result = "";
        for (int i = 1; i <= dimension[0]; i++) {
            double[] row = this.getRow(i);
            result += "[";
            for (int j = 1; j <= dimension[0]; j++) {
                {
                    result += row[j] + "  ";
                }
                result += "]";
                result += "\n";
            }
        }
        return result;
    }

}
