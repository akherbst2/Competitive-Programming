import java.util.Comparator;

/**
 * Created by Alyssa on 7/31/2016.
 */
public static class ProbA {
    public static void main(String[] args)
    {
        Comparator<Note> comparator = new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                if(o1.equals(o2))
                {
                    return 0;
                }
                else if ()
            }
        }




    }

    private class Note{
        String id1;
        String id2;
        public Note(String id1) {
            this.id1 = id1;
            this.id2 = id1;
        }
        public Note(String id1, String id2)
        {
            this.id1 = id1;
            this.id2 = id2;
        }

        @Override
        public boolean equals(Object note)
        {
            Note n = (Note) note;
            if(id1.equals(n.id1))
            {
                return true;
            }
            else if (id1.equals(n.id2)){
                return true;
            }
            else if (id2.equals(n.id1))
            {
                return true;
            }
            else if( id2.equals(n.id2)) return true;
            else return false;

        }
    }
}
