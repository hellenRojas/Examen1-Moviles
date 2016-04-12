package cr.ac.itcr.examen1moviles;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.itcr.examen1moviles.access_data.Connexion;
import cr.ac.itcr.examen1moviles.access_data.FlorRepository;
import cr.ac.itcr.examen1moviles.access_data.IRepository;
import cr.ac.itcr.examen1moviles.entity.Flor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgregarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarFragment extends Fragment {
    private Connexion connexion;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AgregarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarFragment newInstance(String param1, String param2) {
        AgregarFragment fragment = new AgregarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_agregar, container, false);
        final Button addA = (Button)view.findViewById(R.id.btnAgregarFlor);

        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se obtienen los componentes de la interfaz
                EditText etname = (EditText)view.findViewById(R.id.etNombre);
                EditText etnameC = (EditText)view.findViewById(R.id.etNombreC);
                EditText etcolor= (EditText)view.findViewById(R.id.etColor);
                // se verifica que el espacio nombre no este vacio
                if(etname.getText().toString().equals("")){
                    Toast.makeText(getContext().getApplicationContext(), "Complete todos los espacios", Toast.LENGTH_LONG).show();
                }
                else {
                    FlorRepository repository = new FlorRepository(getContext().getApplicationContext());
                    // Se crea un nuevo objeto Flor
                    Flor flor = new Flor();
                    flor.setName(etname.getText().toString());
                    flor.setNameC(etnameC.getText().toString());
                    flor.setColor(etcolor.getText().toString());

                    // Luego se agrega a la base de datos y se le notifica al usuario
                    repository.Save(flor);
                    etname.setText("");
                    etnameC.setText("");
                    etcolor.setText("");
                    Toast.makeText(getContext(),"Registrado correctamente",Toast.LENGTH_SHORT).show();

                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
