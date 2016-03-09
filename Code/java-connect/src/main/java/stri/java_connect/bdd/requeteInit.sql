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




create table competence (




create table aimer (courrielLikant string primary key not null,
                    idCompetence string primary key not null,
                    courrielPropCompetence string primary key not null)


