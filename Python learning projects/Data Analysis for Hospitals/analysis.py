import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
pd.set_option('display.max_columns', None)


def main():
    general = pd.read_csv('test/general.csv')
    prenatal = pd.read_csv('test/prenatal.csv')
    sports = pd.read_csv('test/sports.csv')
    prenatal = prenatal.rename(columns={'HOSPITAL': 'hospital', 'Sex': 'gender'})
    sports = sports.rename(columns={'Hospital': 'hospital', 'Male/female': 'gender'})
    merged = pd.concat([general, prenatal, sports], ignore_index=True)
    merged.drop(columns='Unnamed: 0', inplace=True)
    merged.dropna(axis=0, thresh=1, inplace=True)
    merged = merged.replace("female", "f")
    merged = merged.replace("male", "m")
    merged = merged.replace("woman", "f")
    merged = merged.replace("man", "m")
    merged["gender"].fillna("f", inplace=True)
    merged.fillna(0, inplace=True)
    merged.plot(y='age', kind='hist')
    plt.show()
    merged.diagnosis.value_counts().plot(kind='pie')
    print("The answer to the 1st question: 15 - 35")
    plt.show()
    print("The answer to the 2nd question: pregnancy")
    sns.violinplot(x=merged.hospital, y=merged.height, hue=merged.gender, split=True,
                   inner='quartile')
    plt.show()
    print("The answer to the 3rd question: It's because the sports hospital uses imperial units where the others "
          "use metric")


if __name__ == '__main__':
    main()
