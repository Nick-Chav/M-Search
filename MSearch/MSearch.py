#All libraries here please

import pandas as pd
import json

#USA Filepath Check
filePath = f'MSearch/M-USA-Final.csv'
equivelent_only_USA = pd.read_csv(filePath)

#Info retrieval Denmark
filePath = f'MSearch/M-Denmark-Final.csv'
equivelent_only_Denmark = pd.read_csv(filePath)


def get_Product(ndc_id, database):
    if database == 'usa':
        medicine_info = equivelent_only_USA[equivelent_only_USA['ID'] == ndc_id]
    elif database == 'denmark':
        medicine_info = equivelent_only_Denmark[equivelent_only_Denmark['ID'] == ndc_id]
    else:
        return -2, ''
        #"Database Not Found"

    if not medicine_info.empty:
        return 0, medicine_info
    else:
        return -1, ''
        #"The product was not found."


def ranker(active, form, dosage, data):

    for index, row in data.iterrows():
        pval = 0

        if row['Ingredient'] == active:
            pval += 10
        if row['Form'] == form:
            pval += 5
        if row['Dosage'] == dosage:
            pval += 1

        data.loc[index, 'Rank'] = pval

    data = data.sort_values(by='Rank', ascending=False)
    return data


def get_match(ds, active, form, dosage):
    #Denmark Database
    if ds == 'denmark':
        match = equivelent_only_Denmark[(equivelent_only_Denmark["Ingredient"] == active)]

        if match.empty:
            return 'Not Found'
        else:
            return ranker(active, form, dosage, match)


    #mainly debug
    elif ds == 'both':
        match = equivelent_only_Denmark[(equivelent_only_Denmark["Ingredient"] == active)]
        match2 = equivelent_only_USA[(equivelent_only_USA["Ingredient"] == active)]
        if match.empty:
            return 'Not Found'
        else:
            return match, match2


    #USA Database
    elif ds == 'usa':
        match = equivelent_only_USA[(equivelent_only_USA["Ingredient"] == active)]

        if match.empty:
            return match
        else:
            return ranker(active, form, dosage, match)


    #USA DATABASE
    else:
        return 'Database Unrecognized'




def m_search(ndc, db):
    opposite_db = 'denmark'

    #get product then get matches then make kotlin obj then send back here

    val, product = get_Product(ndc, db)

    if val == -1:
        failure = [['Original-Missing',0,"","","",0.0]]
        df_failure = pd.DataFrame(failure,columns=['Name','ID', 'Ingredient', 'Form', 'Dosage', 'Rank'])
        return df_failure.to_json(orient='records')
    elif val == -2:
        failure = [['Original-Missing',0,"","","",0.0]]
        df_failure = pd.DataFrame(failure,columns=['Name','ID', 'Ingredient', 'Form', 'Dosage', 'Rank'])
        return df_failure.to_json(orient='records')
    else:
        ingredient = product.iloc[0, 2]
        form = product.iloc[0, 3]
        dosage = product.iloc[0, 5]
        matches = get_match(opposite_db, ingredient, form, dosage)

        if isinstance(matches, str):
            failure = [['Not-Found',0,"","","",0.0]]
            df_failure = pd.DataFrame(failure,columns=['Name','ID', 'Ingredient', 'Form', 'Dosage', 'Rank'])
            return df_failure.to_json(orient='records')
        else:
            product['ID'] = 1
            product = product.drop('Route', axis=1)
            product['Rank'] = 1.0

            jsonProduct = product.to_json(orient='records')
            jsonMatches = matches.to_json(orient='records')

            dict1 = json.loads(jsonProduct)
            dict2 = json.loads(jsonMatches)


            merged_list = dict1 + dict2
            jData = json.dumps(merged_list, indent=2)

            return jData
