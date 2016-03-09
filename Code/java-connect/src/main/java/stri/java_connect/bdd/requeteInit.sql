/* Suppression des tables existantes */

drop table if exists utilisateur;

create table utlisateur (courriel string primary key not null,
                        motDePasse string not null,
                        nom string not null,
                        privilege string not null,
                        telephone string not null,
                        dateDiplome long not null,
                        permissionLecture string not null,
                        )


create table competence (idCompetence integer auto_increment not_null,
			nomCompetence string,
			idCompetence  primary key)

create table posseder (courriel string,
			idCompetence integer)

create table envoyerMessage (courrielSource string not null,
				courrielDestinataire string not null,
				dateMessage timestamp not null,
				message text,
				foreing key (corrielSource) references utilisateur(courriel),
				foreing key (courrielDestinataire) references utilisateur(courriel),
				primary key (courrielSource,courrielDestinataire,dateMessage)
				)


create table aimer (courrielLikant string not null,
                    idCompetence string not null,
                    courrielPropCompetence string not null,
		foreing key (courrielLikant) references utilisateur(courriel),
		foreing key (courrielPropCompetence) references utilisateur(courriel),
		primary key (courrielLikant,courrielPropCompetence,idCompetence)
		)


