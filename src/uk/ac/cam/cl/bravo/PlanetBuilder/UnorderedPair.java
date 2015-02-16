package uk.ac.cam.cl.bravo.PlanetBuilder;

//Source: http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples

public class UnorderedPair<L,R> {

	  private final L left;
	  private final R right;

	  public UnorderedPair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (!(o instanceof UnorderedPair)) return false;
	    UnorderedPair pairo = (UnorderedPair) o;
	    return (this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight())) 
	           ||
	           (this.left.equals(pairo.getRight()) &&
	    	   this.right.equals(pairo.getLeft()));
	  }

	}