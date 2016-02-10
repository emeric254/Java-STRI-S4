/**
 *
 */
package stri.java_connect.modele;

import java.util.ArrayDeque;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import stri.java_connect.utils.JSONLoader;

/**
 * @author emeric, remi
 *
 */
public class Utilisateur
{
    private final static String glt = "\"";
    private final static String separ = "\" : \"";
    private final static String vgl = "\" ,";
    private final static String courrielJSON = glt + "courriel" + separ;
    private final static String telephoneJSON = glt + "telephone" + separ;
    private final static String nomJSON = glt + "nom" + separ;
    private final static String dateDiplomeJSON = glt + "datediplome" + separ;
    private final static String competencesJSON = glt + "competences" + glt + " : [";

    private String motDePasse;
    private String nom;
    private Long dateDiplome;
    private String telephone;
    private String courriel;
    private String permissionLecture;
    private String privilege;
    private ArrayDeque<String> Competences;

//
// Constructors
//

    /**
     * Creation d'un Utilisateur avec des valeurs par defaut
     *
     */
    public Utilisateur ()
    {
        motDePasse = "";
        nom = "";
        dateDiplome = (long) -1;
        telephone = "";
        courriel = "";
        permissionLecture = "utilisateur";
        // TODO permissions plus detaillees
        /*
        permissionLecture = "{"
            + "'nom':'anonyme',"
            + "'datediplome':'anonyme',"
            + "'telephone':'anonyme',"
            + "'courriel':'anonyme',"
            + "'competences':'anonyme'"
            + "}";
        */
        privilege = "utilisateur";
        Competences = new ArrayDeque<String>();
    };

    /**
     * Creation d'un Utilisateur avec son courriel et son mot de passe
     * 
     * @param pCourriel courriel qui identifie l'utilisateur
     * @param pMdp mot de passe de l'utilisateur
     */
    public Utilisateur (String pCourriel, String pMdp)
    {
        this();
        setCourriel(pCourriel);
        setMotDePasse(pMdp);
    };

    /**
     * Creation d'un Utilisateur avec son nom, telephone et courriel
     * 
     * @param pNom nom de l'utilisateur
     * @param pTelephone telephone de l'utilisateur
     * @param pCourriel courriel qui identifie l'utilisateur
     */
    public Utilisateur (String pNom, String pTelephone, String pCourriel)
    {
        this();
        setNom(pNom);
        setTelephone(pTelephone);
        setCourriel(pCourriel);
    };

//
// Methods
//

    //
    // Accessor methods
    //

    /**
     * Set the value of motDePasse
     * 
     * @param newVar the new value of motDePasse
     */
    public void setMotDePasse (String newVar)
    {
        motDePasse = newVar;
    }

    /**
     * Get the value of motDePasse
     * 
     * @return the value of motDePasse
     */
    public String getMotDePasse ()
    {
        return motDePasse;
    }

    /**
     * Set the value of nom
     * 
     * @param newVar the new value of nom
     */
    public void setNom (String newVar)
    {
        nom = newVar;
    }

    /**
     * Get the value of nom
     * 
     * @return the value of nom
     */
    public String getNom ()
    {
        return nom;
    }

    /**
     * Set the value of dateDiplome
     * 
     * @param newVar the new value of dateDiplome
     */
    public void setDateDiplome (Long newVar)
    {
        dateDiplome = newVar;
    }

    /**
     * Set the value of dateDiplome
     * 
     * @param newVar the new value of dateDiplome
     */
    public void setDateDiplomeFromDate (Date newVar)
    {
        setDateDiplome(newVar.getTime());
    }

    /**
     * Get the value of dateDiplome
     * 
     * @return the value of dateDiplome
     */
    public Long getDateDiplome ()
    {
        return dateDiplome;
    }

    /**
     * Get the value of dateDiplome as a Date object
     * 
     * @return the value of dateDiplome as a Date object
     */
    public Date getDateDiplomeAsDate ()
    {
        return new Date(getDateDiplome());
    }

    /**
     * Set the value of telephone
     * 
     * @param newVar the new value of telephone
     */
    public void setTelephone (String newVar)
    {
        telephone = newVar;
    }

    /**
     * Get the value of telephone
     * 
     * @return the value of telephone
     */
    public String getTelephone ()
    {
        return telephone;
    }

    /**
     * Set the value of courriel
     * 
     * @param newVar the new value of courriel
     */
    public void setCourriel (String newVar)
    {
        courriel = newVar;
    }

    /**
     * Get the value of courriel
     * 
     * @return the value of courriel
     */
    public String getCourriel ()
    {
        return courriel;
    }

    /**
     * Set the value of permissionLecture
     * 
     * @param newVar the new value of permissionLecture
     */
    public void setPermissionLecture (String newVar)
    {
        permissionLecture = newVar;
    }

    /**
     * Get the value of permissionLecture
     * 
     * @return the value of permissionLecture
     */
    public String getPermissionLecture ()
    {
        return permissionLecture;
    }

    /**
     * Set the value of privilege
     * 
     * @param newVar the new value of privilege
     */
    public void setPrivilege (String newVar)
    {
        privilege = newVar;
    }

    /**
     * Get the value of privilege
     * 
     * @return the value of privilege
     */
    public String getPrivilege ()
    {
        return privilege;
    }

    /**
     * Get the value of Competences
     * 
     * @return the value of Competences
     */
    public ArrayDeque<String> getCompetences ()
    {
        // return a clone to avoid some security issues
        return Competences.clone();
    }

    /**
     * Set the value of Competences
     * 
     * @param newVar the new value of Competences
     */
    public void setCompetences (ArrayDeque<String> newVar)
    {
        Competences = newVar;
    }

    /**
     * Add a Competence
     * 
     * @param newVar the new Competence to add
     */
    public void addCompetence (String newVar)
    {
        Competences.add(newVar);
    }

//
// Other methods
//

    /**
     * Get Utilisateur JSON String representation
     * 
     * @return the Utilisateur object as a JSON formated String
     */
    @Override
    public String toString()
    {
        String chaine = "{";

        chaine += glt + "motdepasse" + separ + motDePasse + vgl;

        chaine += nomJSON + nom + vgl;

        chaine += dateDiplomeJSON + dateDiplome + vgl;

        chaine += telephoneJSON + telephone + vgl;

        chaine += courrielJSON + courriel + vgl;

        // TODO temporaire avec permissionLecture simple
        chaine += glt + "permissionlecture" + separ + permissionLecture + vgl;

        chaine += glt + "privilege" + separ + privilege + vgl;

        chaine += competencesJSON;
        for(String temp : Competences)
        {
            chaine += glt + temp + vgl;
        }
        if(Competences.size() > 0)
            chaine = chaine.substring(0, chaine.length()-1);
        chaine += "] }";
        return chaine;
    }
    
    /**
     * Get Utilisateur JSON String representation reduced for Anonymous reading
     * 
     * @return the Utilisateur object as a JSON formated String as Anonymous readable
     */
    public String toStringAnonyme()
    {
        String chaine = "{";
        chaine += nomJSON + nom + vgl;
        if("anonyme".equals(permissionLecture))
        {
            chaine += courrielJSON + courriel + vgl;
            chaine += telephoneJSON + telephone + vgl;
            chaine += dateDiplomeJSON + dateDiplome + vgl;
            chaine += competencesJSON;
            for(String temp : Competences)
            {
                chaine += glt + temp + vgl;
            }
            if(Competences.size() > 0)
                chaine = chaine.substring(0, chaine.length()-1);
            chaine += "]";
        }
        chaine += "}";
        
        System.out.println(chaine);
        return chaine;
    }
    
    /**
     * Get Utilisateur JSON String representation reduced for other User reading
     * 
     * @return the Utilisateur object as a JSON formated String as User readable
     */
    public String toStringUtilisateur()
    {
        String chaine = "{";
        chaine += nomJSON + nom + vgl;
        chaine += courrielJSON + courriel + vgl;
        chaine += telephoneJSON + telephone + vgl;
        chaine += dateDiplomeJSON + dateDiplome + vgl;
        chaine += competencesJSON;
        for(String temp : Competences)
        {
            chaine += glt + temp + vgl;
        }
        if(Competences.size() > 0)
            chaine = chaine.substring(0, chaine.length()-1);
        chaine += "] }";
        
        System.out.println(chaine);
        return chaine;
    }

    /**
     * Load informations from a JSON representation String
     * 
     * @param json representing an Utilisateur object
     */
    public void fromJSONString(String json)
    {
        // TODO import from json string
        JSONObject details = new JSONObject(json);
        setCourriel(JSONLoader.readStringJSONObject(details, "courriel"));
        setMotDePasse(JSONLoader.readStringJSONObject(details, "motdepasse"));
        setNom(JSONLoader.readStringJSONObject(details, "nom"));
        setPermissionLecture(JSONLoader.readStringJSONObject(details, "permissionlecture"));
        setTelephone(JSONLoader.readStringJSONObject(details, "telephone"));
        try { setDateDiplome(Long.parseLong(JSONLoader.readStringJSONObject(details, "datediplome"))); } catch (NumberFormatException e) {}
        setPrivilege(JSONLoader.readStringJSONObject(details, "privilege"));
        try
        {
        	JSONArray listeCompetences = details.getJSONArray("competences");
	        for (Object object : listeCompetences)
	        {
	            Competences.add((String) object);
	        }
        } catch (JSONException e) {}
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Utilisateur clone()
    {
        Utilisateur copie = new Utilisateur();
        copie.fromJSONString(this.toString());
        return copie;
    }

    /**
     * Make a clone of this object with only anonymous readable attributes
     * 
     * @return a clone with only anonymous readable attributes
     */
    public Utilisateur cloneAnonyme()
    {
        Utilisateur copie = new Utilisateur();
        copie.fromJSONString(this.toStringAnonyme());
        return copie;
    }

    /**
     * Make a clone of this object with only user readable attributes
     * 
     * @return a clone with only user readable attributes
     */
    public Utilisateur cloneUtilisateur()
    {
        Utilisateur copie = new Utilisateur();
        copie.fromJSONString(this.toStringUtilisateur());
        return copie;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj.toString().equals(this.toString());
    }
}
