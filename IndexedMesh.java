package xw302;

/**
 * Class that holds a mesh's data using indices
 * 
 */
public class IndexedMesh extends Mesh {
        
        /**
         * Indices of this IndexedMesh
         */
        protected int[] indices;
        
        /**
         * Returns the indices for this IndexedMesh.
         * 
         * @return the indices
         */
        public int[] getIndices() {
                return indices;
        }
        
}
