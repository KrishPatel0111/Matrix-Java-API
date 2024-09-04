import components.standard.Standard;;

/**
 * Matrix kernel component with primary methods.
 *
 * @mathsubtypes <pre>
 * MATRIX is an two dimensional array of doubles
 * exemplar array[][]
 * constraint array[][] is not empty
 * </pre>
 * @mathmodel type MatrixKernel is modeled by SEQUENCE
 * @initially <pre>
 * ():
 *  ensures
 *   this = [0]
 * (array[][]):
 *  requires
 *   array is not empty
 *  ensures
 *   this = array[][]
 * </pre>
 */

public interface MatrixKernel extends Standard<Matrix> {

    /**
     * gets the mth row of {@code this}.
     *
     * @param m
     *            the {@code int} row to get
     * @requires {@code 1<=m<=number of row of {@code this}}.
     * @ensures {@code row} is mth row of {@code this}
     * @return the mth row
     *
     */
    double[] getRow(int m);

    /**
     * gets the nth column of {@code this}.
     *
     * @param n
     *            the {@code int} column to get
     * @requires {@code 1<=n<=number of column of {@code this}}.
     * @ensures {@code column} is nth column of {@code this}
     * @return the nth column
     *
     */
    double[] getColumn(int n);

    /**
     * adds {@code newRow} as the last row of {@code this}.
     *
     * @param newRow
     *            row to be added
     * @requires {@code newRow.length=number of column of {@code this}}
     * @updates this
     * @ensures #this*newRow = this
     */

    void addRow(double[] newRow);

    /**
     * adds {@code newColumn} as the last column of {@code this}.
     *
     * @param newColumn
     *            column to be added
     * @requires {@code newColumn.length=number of rows of {@code this}}
     * @updates this
     * @ensures #this*newColumn = this
     */

    void addColumn(double[] newColumn);

    /**
     * gets the dimension of {@code this}.
     *
     * @return the number of rows and columns
     */

    double[] getDimension();
}
