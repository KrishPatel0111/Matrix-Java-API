/**
 * {@code MatrixKernel} enhanced with secondary methods.
 */
public interface Matrix extends MatrixKernel {

    /**
     * adds {@code matrix} into {@code this}.
     *
     * @param matrix
     *            the matrix to be added
     * @updates this
     * @requires dim(@code this)=dim((@code matrix)
     * @ensures #this + matrix = this
     */
    void add(double[][] matrix);

    /**
     * multiplies the elements of @code this by @code scalar.
     *
     * @param scalar
     *            the scalar to be multiplied
     * @updates this
     * @ensures scalar*#this=this
     */

    void multiplyByScalar(double scalar);

    /**
     * {@code this} is being multiplied by {@code matrix}.
     *
     * @param matrix
     *            the matrix to be multiplied
     * @updates this
     * @requires @code this number of columns = @code matrix number of rows
     * @ensures #this*matrix=this
     */
    void multiply(double[][] matrix);

    /**
     * updates {@code this} to its inverse.
     *
     * @updates this
     * @requires @code this is invertible
     * @ensures #this*this = identity matrix
     *
     */

    void inverse();

    /**
     * updates {@code this} to its transpose.
     *
     * @updates this
     * @ensures #this=this after changing the rows and columns
     */

    void transpose();

    /**
     * returns true if {@code this} is symmetric.
     *
     * @return true if {2code this} is symmetric
     */

    boolean isSymmetric();

    /**
     * returns true if {@code this} is zero matrix.
     *
     * @return true if {@code this} is zero matrix
     */

    boolean isZero();

    /**
     * returns true if {@code this} is identity matrix.
     *
     * @return true if {@code} this is identity matrix
     */

    boolean isIdentity();

    /**
     * returns true if {@code this} is invertible.
     *
     * @return true if {@code} this is invertible.
     */

    boolean isInvertible();

    /**
     * swaps the ath and bth row of {@code this}.
     *
     * @param a
     *            the row to be swapped
     * @param b
     *            the row to be swapped with
     *
     * @updates this
     * @requires 1<=a,b<={@code this} number of rows
     * @ensures #this= this with ath and bth rows swapped
     */

    void operation1(int a, int b);

    /**
     * multiplies bth row by {@code scalar} in {@code this}.
     *
     * @param b
     *            the row to be multiplied with
     * @param scalar
     *            the scalar to be multiplied
     * @updates this
     * @requires 1<=b<={@code this} number of rows
     * @ensures #this*scaler in row b = this
     */

    void operation2(int b, double scalar);

    /**
     * multiplies bth row by {@code scalar} and adds it to ath row in
     * {@code this}.
     *
     * @param a
     *            the row to be added with
     * @param b
     *            row to be multiplied and added
     * @param scalar
     *            the scalar to be multiplied
     * @updates this
     * @requires 1<=a,b<={@code this} number of rows
     * @ensures #this ath row added by k*bth row = this
     */

    void operation3(int a, int b, double scalar);

}
