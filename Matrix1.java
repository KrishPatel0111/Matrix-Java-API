import components.sequence.Sequence;

/**
 *
 * @author krish
 *
 */
public abstract class Matrix1 implements MatrixKernel {

    private Sequence rep;

    private void createNewRep() {
        //creates new empty string
        this.rep = new Sequence<Double>();

    }

    @Override
    public double[] getRow(int m) {
        return null;
    }
}
